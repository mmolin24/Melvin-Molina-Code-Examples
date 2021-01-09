import multiprocessing as mp
import re
import sys
from multiprocessing import Lock, Process, Queue
from functools import reduce
import requests
from bs4 import BeautifulSoup
from lxml import html
from urllib.parse import urljoin

# Main function for url processing
def get_html_func(currQueue, l):

    # popping url from the Queue to use as page fetched
    url = currQueue.get() 
    finalLink = url + "/\n"
    
    try: # try request get in case of any exception 
        response = requests.get(url, timeout=3)
    except: #if there's any exception skip this url
        return 

    rep = BeautifulSoup(response.text, 'lxml')
    # Only move forward with successful get requests
    if response.status_code == 200:
        linkList = []

        # Rather than usage of reduce, usage of multiple "python" type list comprehension is more readable
        links = [str(a['href']) for a in rep.find_all('a', href=True)]

        # Process the href links with helper function and filters
        links = list(map(lambda currLink: validateLink(currLink, url), links))
        [linkList.append(x) for x in links if x not in linkList]
        linkList = links = list(filter(None, linkList))
        links = [addFoundLinks(currLink, currQueue)  for currLink in linkList]

        # remove all unneeded values using a regex filter
        regex = re.compile(r"^h")
        links = list(filter(regex.search , links))
        
        # Adding links to string to then print
        for link in links:
            finalLink +=  "\t" + link + "\n"

        finalLink = finalLink.strip()
    # Using lock to print out to console steadily         
        l.acquire()
        try:
            print(finalLink)                                       
        finally:
            l.release() 
            response.close()
    # returning the string as an array makes testing easier
    return finalLink.split()


# Place link into the queue if it's valid
def addFoundLinks(link: str, currentQ):
    currentQ.put(link)    
    return link

# Apply urllib to tidy links
def validateLink(link :str, originalUrl :str):
    return urljoin(originalUrl, link)


if __name__ == '__main__':

    # properties that will be used in the multiprocessing section
    lock = Lock()
    all = Queue()
    # retrieve command line argument with link
    all.put(str(sys.argv[1]))

    # Begin the creation of multiple processes
    while all:
        proc = Process(target= get_html_func, args=(all,lock))
        proc.start()

    # Collect children (may never reach this)
    for child in mp.active_children():
        child.join()

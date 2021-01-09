<h1>About This Project</h1>
<p>
This program is a Web Crawler created in Python which works with multiple processes. With the usage of multiple modules including requests, BeautifulSoup, multiprocessing, and many more. The script will only fetch pages which return with GET code 200 (Sucessful) as a safety precaution.The purpose of the script is to fetch all href tags and print in the format following format: 
  
```
<URL of page fetched>
  <URL found on page>
  <URL found on page>
<URL of page fetched>
  <URL found on page>
  <URL found on page>
```

Please read through the following steps to ensure script is ran correctly.
</p>
<h1>Setup</h1><p>
To get started we must ensure that all the packages are installed to be able to run the script properly. 
First off, if you have not installed python3 or above already, follow the link to ensure proper installation:
https://www.python.org/downloads/
second, if you do not have pip installed already, please follow the link to ensure you have pip installed.
https://pip.pypa.io/en/stable/installing/#

Assuming you have already cloned this directory run the following command in your linux/bash command console:

```
$ pip install .
```
This will download all the packages required to run the program.
</p>
<p>
  As a <b>WARNING</b> this program may not automatically stop, due to the arbitrary amount of possible links fetched. Due to the structure of the program, please be ready to manually stop the program from running once you've received sufficient links fetched and listed. The command to stop a python script can vary by enviroment, but typically the command <kbd>Ctrl</kbd> + <kbd>Z</kbd> then  
  
  `$ killall python3` will work in killing all processes created by this script and all python3 scripts.
  
  <h1>Running With Your Link</h1> 
  <p>
    To search scrape your link, type in the following command:
  
    $ python3 Crawler.py $YOURLINK
  and replace 
  `$YOURLINK` with the http/https link you'd like to begin web scraping. 
  </p>
  </p>
  
<h1>Running My Provided Tests</h1><p>
   Within this repository, I've included multiple tests which created by myself. The tests will run and print out the output alongside the status of the tests. The current links which will be tested are: https://www.rescale.com/, https://www.alexa.com/topsites, http://quotes.toscrape.com/, and https://www.geeksforgeeks.org/. The links being tested with AssertIn functions are manually selected off the website to test the ability to fetch visible href tags. The command below will run all of the tests within the TestCrawler file:
  
  ```
  $ python3 TestCrawler.py
  ```
  
  
  </p>

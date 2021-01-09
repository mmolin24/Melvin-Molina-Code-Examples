import unittest
import Crawler
import multiprocessing as mp
from multiprocessing import Queue, Lock
class TestingCrawler(unittest.TestCase):

    # Setting up the queue and lock for every test
    def setUp(self):
        self.testQueue = Queue()
        self.lock = Lock()

    # Print statement to seperate fetched links
    def tearDown(self):
        print("Next Test")


    def test_rescale(self):
        url = "https://www.rescale.com/"
        self.testQueue.put(url)
        result = Crawler.get_html_func(self.testQueue,self.lock)

        self.assertIn("https://resources.rescale.com/", result)
        self.assertIn("https://www.linkedin.com/company/rescale/", result)
        self.assertIn("https://twitter.com/rescaleinc", result)
        self.assertIn("https://www.facebook.com/rescaleinc/", result)


    def test_alexa_top100(self):
        url = "https://www.alexa.com/topsites"
        self.testQueue.put(url)
        result = Crawler.get_html_func(self.testQueue,self.lock)

        self.assertIn("https://www.alexa.com/siteinfo/google.com", result)
        self.assertIn("https://www.alexa.com/siteinfo/youtube.com", result)
        self.assertIn("https://www.alexa.com/plans", result)
        self.assertIn("https://www.alexa.com/topsites/countries", result)
        self.assertIn("https://www.alexa.com/siteinfo/reddit.com", result)
        self.assertIn("https://www.alexa.com/about", result)


    def test_toscrape(self):
        url = "http://quotes.toscrape.com/"
        self.testQueue.put(url)
        result = Crawler.get_html_func(self.testQueue,self.lock)

        self.assertIn("http://quotes.toscrape.com/tag/choices/page/1/", result)
        self.assertIn("http://quotes.toscrape.com/tag/inspirational/page/1/", result)
        self.assertIn("http://quotes.toscrape.com/tag/inspirational/", result)
        self.assertIn("http://quotes.toscrape.com/author/Thomas-A-Edison", result)
        self.assertIn("http://quotes.toscrape.com/page/2/", result)
        self.assertIn("http://quotes.toscrape.com/author/Marilyn-Monroe", result)


    def test_geeksforgeeks(self):
        url = "https://www.geeksforgeeks.org/"
        self.testQueue.put(url)
        result = Crawler.get_html_func(self.testQueue,self.lock)

        self.assertIn("https://www.geeksforgeeks.org/about/", result)
        self.assertIn("https://www.geeksforgeeks.org/fundamentals-of-algorithms/", result)
        self.assertIn("https://www.geeksforgeeks.org/contribute/", result)
        self.assertIn("https://www.geeksforgeeks.org/internship/", result)
        self.assertIn("https://www.geeksforgeeks.org/computer-science-projects/", result)
        self.assertIn("https://twitter.com/geeksforgeeks", result)            

if __name__ == "__main__":
    unittest.main()
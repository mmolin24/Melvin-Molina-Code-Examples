import org.jsoup.nodes.Document;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class SpiderCrawler {

    Set<String> allURL;
    Queue<String> queue;
    final int MAX = 3;
    int count = 0;

    /*
        Constructor that begins the process with
        declaring the hashset and prio queue
     */
    SpiderCrawler(URL root){

        // initializes the hashset and the priority queue while adding the first element
        allURL = new HashSet<>();
        allURL.add(root.toString());

        queue = new PriorityQueue<String>();
        queue.add(root.toString());

        // calls makeWeb to begin organizing url traces
        makeWeb(queue, allURL);
    }

    /*
        Driver function that will repeatedly call crawl until no more websites
     */
    void makeWeb(Queue<String> queue, Set<String> allURL){

        // continues until queue is done (almost never) or reached max amount of pages fetched
        if(queue.size() > 0 && count < MAX) {

            // prints out the top of the queue
            System.out.println(queue.peek());
            // adds to keep track of how many URLs have been fetched
            count++;
            // calls crawl with queue and the set
            crawl(queue, allURL);
        }
    }

    /*
        gets the names o the websites in the URL
        prints them all while adding to the queue
     */
    void crawl(Queue<String> queue, Set<String> urls){

        try{
            // creates a document from the fetched URL
            Document doc = Jsoup.connect(queue.poll()).get();

            // selects all fields with the prefix http into an elements object
            Elements sites = doc.select("a[href^=http]");

            // iterates through the elements
            for(Element site: sites) {

                // checks if the attribute of the element is href
                String text = site.attr("abs:href");

                // makes a URL field to double check if it's a usable link
                URL link = new URL(text);

                // if MalformedURLE was not caught at this stage then by using the add function
                // the set checks if it already contains, so we are not repeating URLs
                if (urls.add(link.toString())){
                    // adds the link in string to the queue
                    queue.add(link.toString());
                    // prints out a tab and the link to show this' a "child" URL
                    System.out.println("\t " + link);
                }
            }
        } catch (MalformedURLException e) {
            // here to make sure all the links added to the set/queue are valid
        } catch(IOException e){
            // for the queue.poll.get due to the potential thrown IOE
        }

        // makeWeb with the updated queue and set
        makeWeb(queue, urls);

    }


    public static void main(String[] args) throws MalformedURLException{

        // calls constructor with a new instance of the url provided in argument
        SpiderCrawler spider = new SpiderCrawler(new URL("https://www.rescale.com/"));
    }
}

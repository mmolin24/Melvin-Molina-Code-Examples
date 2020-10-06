import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.security.provider.ConfigFile;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;


class SpiderCrawlerTest {

    SpiderCrawler spider;
    @BeforeEach
    void setUp() throws MalformedURLException {
        SpiderCrawler spider;
    }

    @AfterEach
    void tearDown() {
        spider = null;
    }

    @Test
    void test1() throws MalformedURLException {
        // this is with MAX set to 3
        spider = new SpiderCrawler(new URL("https://www.rescale.com/"));
        assertEquals("http://rescale.com/login", spider.queue.peek());

    }
}
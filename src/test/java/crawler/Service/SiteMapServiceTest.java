package crawler.Service;

import com.sandbox.crawler.Service.SiteMapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteMapServiceTest {

    @Autowired
    private SiteMapService siteMapService;

    @Test
    public void parseUrl() throws Exception {
        assertEquals(1038, siteMapService.parseUrl("http://www.3suisses.fr/sitemap-arbo.xml").size());
    }

    @Test
    public void parseUrlToPages() throws Exception {
        assertEquals(1038, siteMapService.parseUrlToPages("3suisse","http://www.3suisses.fr/sitemap-arbo.xml").size());
    }

    @Test
    public void parseUrlUsingRobotTxt() throws Exception {
       assertEquals(6519, siteMapService.parseUrlUsingRobotTxt("3suisse", "http://www.3suisses.fr/robots.txt").size());
    }

}
package crawler.Service;

import com.sandbox.crawler.Service.CrawlerEngineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerEngineServiceTest {

    @Autowired
    private CrawlerEngineService crawlerEngineService;

    @Test
    public void crawler() throws Exception {
        //crawlerEngineService.crawler("1-2-3", "http://www.etam.com/robots.txt");
        //crawlerEngineService.crawler("1-2-3", "http://www.1-2-3.com/robots.txt");
        //crawlerEngineService.crawler("1-2-3", "http://www.1-2-3.fr/robots.txt");
        //crawlerEngineService.crawler("wonderbra", "http://wonderbra.fr/robots.txt");
        //crawlerEngineService.crawler("playtex", "http://www.playtex.fr/robots.txt");
        crawlerEngineService.crawler("3suisses", "http://www.3suisses.fr/robots.txt");
    }

}
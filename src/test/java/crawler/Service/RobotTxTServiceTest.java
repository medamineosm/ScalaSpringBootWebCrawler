package crawler.Service;

import com.sandbox.crawler.Service.RobotTxTService;
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
public class RobotTxTServiceTest {

    @Autowired
    private RobotTxTService robotTxTService;

    @Test
    public void findSiteMap() throws Exception {
        assertEquals(10, robotTxTService.findSiteMap( "http://www.3suisses.fr/robots.txt").size());
        assertEquals(5, robotTxTService.findSiteMap( "http://www.1-2-3.fr/robots.txt").size());
        assertEquals(1, robotTxTService.findSiteMap( "http://www.playtex.fr/robots.txt").size());
    }

    @Test
    public void googleBotSiteMap() throws Exception {
        System.out.println(robotTxTService.googleBotSiteMap( "http://www.1-2-3.fr/robots.txt"));
    }

}
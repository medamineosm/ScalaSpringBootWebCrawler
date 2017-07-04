package crawler.Component;

import com.sandbox.crawler.Component.Spyder;
import com.sandbox.crawler.Service.WebPageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpyderTest {

    @Autowired
    private WebPageService webPageService;
    @Autowired
    private Spyder spyder;

    private String domaine;
    private String client;
    @Before
    public void setUp() throws Exception {
        domaine = "www.1-2-3.fr";
        client = "1-2-3";
        spyder.setPageFromSiteMap(new LinkedList<>(webPageService.findAllByDomaine(domaine)));
    }

    @Test
    public void explore() throws Exception {
        spyder.explore(client);
    }

    @Test
    public void getHtmlContentByDomaine() throws Exception {
        spyder.getHtmlContentByDomaine(domaine);
    }
}
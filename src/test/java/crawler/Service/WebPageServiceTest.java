package crawler.Service;

import com.sandbox.crawler.Model.WebPage;
import com.sandbox.crawler.Service.WebPageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebPageServiceTest {

    @Autowired
    private WebPageService webPageService;

    private String url;
    private String client;
    private String domaine_FR;
    private String domaine_US;
    private WebPage page;

    @Before
    public void setUp() throws Exception {

        this.client = "1-2-3";
        this.domaine_FR ="www.1-2-3.fr";
        this.domaine_US ="www.1-2-3.com";
        this.url ="http://www.3suisses.fr/FrontOfficePortail/catalogue_fra/femme/vetements-chaussures/tous-les-jeans/jeans-boyfriend.html";
        this.page = new WebPage(
                "1-2-3",
                "www.1-2-3.fr",
                "http://www.1-2-3.fr/pret-a-porter/nouveautes/",
                new Date(),
                200,
                false);
    }

    @Test
    public void getAllWebPage() throws Exception {
        assertEquals(13069, webPageService.getAllWebPage().size());
    }

    @Test
    public void saveWebPage() throws Exception {
        List<WebPage> listWebPage = new ArrayList<>();
        listWebPage.add(this.page);
        assertNotEquals(null, webPageService.saveWebPage(listWebPage));
    }

    @Test
    public void findAllByClient() throws Exception {
        assertEquals(6843, webPageService.findAllByClient(this.client).size());
    }

    @Test
    public void findAllByDomaine() throws Exception {
        assertEquals(1741, webPageService.findAllByDomaine(this.domaine_US).size());
        assertEquals(891, webPageService.findAllByDomaine(this.domaine_FR).size());
    }

    @Test
    public void findOneByUrl() throws Exception {
        assertNotNull(webPageService.findOneByUrl(this.url));
    }

}
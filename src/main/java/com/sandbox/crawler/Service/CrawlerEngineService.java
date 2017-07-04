package com.sandbox.crawler.Service;

import com.sandbox.crawler.Model.WebPage;
import crawler.StartingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@Service
public class CrawlerEngineService {

    @Autowired
    private SiteMapService siteMapService;
    @Autowired
    private WebPageService webPageService;

    public void crawler(String client, String robotTxt){
        System.out.println(("******************** Robot TxT And SiteMap Process ********************").toUpperCase());
        Set<WebPage> pages = RobotTxTAndSiteMapProcess(client, robotTxt);
        System.out.println(("******************** Saving WebPage From SiteMap Process ********************").toUpperCase());
        webPageService.saveBatchWebPage(pages);
    }

    private Set<WebPage> RobotTxTAndSiteMapProcess(String client, String robotTxTUrl){
        return siteMapService.parseUrlUsingRobotTxt(client, robotTxTUrl);
    }

    public void ScalaCrawl(String url){
        StartingPoint.crawl("http://www.1-2-3.fr/");
    }
}

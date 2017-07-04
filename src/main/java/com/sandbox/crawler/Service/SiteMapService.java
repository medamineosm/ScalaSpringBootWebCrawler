package com.sandbox.crawler.Service;

import com.sandbox.crawler.Model.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@Service
public class SiteMapService {

    @Autowired
    private RobotTxTService robotTxTService;
    private Set<String> urls_404 = new HashSet<>();

    public Set<String> parseUrl(String siteMapUrl){
        Set<String> urls = new LinkedHashSet<>();
        try {
            Element doc = Jsoup.connect(siteMapUrl.trim()).get();
            for(Element url : doc.getElementsByTag("loc")){
                urls.add(url.text());
                //System.out.println(url.text());
            }
            return urls;
        } catch (IOException e) {
            e.printStackTrace();
            urls_404.add(siteMapUrl.trim());
            return  urls;
        }
    }

    public Set<WebPage>  parseUrlToPages(String client,String siteMapUrl){

        Set<String> urls = this.parseUrl(siteMapUrl);
        Set<WebPage> pages = new HashSet<>();
        for (String url: urls){
            pages.add(WebPageService.urlToWebPage(client, url,200, true));
        }

        return pages;
    }

    public Set<WebPage> parseUrlUsingRobotTxt(String client, String robotTxtUrl){
        Set<WebPage> pages = new HashSet<>();
        Set<String> sitemapXml= robotTxTService.findSiteMap(robotTxtUrl);
        for(String site : sitemapXml){
            System.out.println("PARSING : ["+site+"]");
            pages.addAll(parseUrlToPages(client,site));
        }

        System.out.println("Pages size : " + pages.size());

        return pages;
    }
}

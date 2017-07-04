package com.sandbox.crawler.Service;

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
public class RobotTxTService {

    @Autowired
    private FileService fileService;

    public Set<String> findSiteMap(String urlRobotTxtFile){
        Set<String> siteMap_1 = fileService.readTxtFileByUrl(urlRobotTxtFile);
        if(siteMap_1.size()> 1)
            return siteMap_1;
        else
            return googleBotSiteMap(urlRobotTxtFile);
    }

    public Set<String> googleBotSiteMap(String urlRobotTxtFile){
        Set<String> sitemapIndex = fileService.readTxtFileByUrl(urlRobotTxtFile);
        Set<String> sitemap = new HashSet<>();
        for(String index : sitemapIndex){
            if(index.endsWith(".xml")){
                sitemap.addAll(parseSiteMapFromXML(index.trim()));
            }
        }
        return sitemap;
    }

    private Set<String> parseSiteMapFromXML(String siteMapUrl){
        Set<String> urls = new LinkedHashSet<>();
        try {
            Element doc = Jsoup.connect(siteMapUrl.trim()).get();
            for(Element url : doc.getElementsByTag("loc")){

                urls.add(url.text());
                System.out.println("Found value: " + url.text());
            }
            return urls;
        } catch (IOException e) {
            e.printStackTrace();
            return  urls;
        }
    }
}

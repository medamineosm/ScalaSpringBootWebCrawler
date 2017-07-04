package com.sandbox.crawler.Controller;

import com.sandbox.crawler.Service.CrawlerEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ouasmine on 06/06/2017.
 */
@RestController
public class CrawlerController {

    @Autowired
    private CrawlerEngineService crawlerEngineService;

    @GetMapping("StartScalaCrawler")
    public void StartScalaCrawler(@RequestParam String url){
        crawlerEngineService.ScalaCrawl(url);
    }
}

package com.sandbox.crawler.Service;

import com.google.common.collect.Lists;
import com.sandbox.crawler.Model.WebPage;
import com.sandbox.crawler.Repository.WebPageRepository;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@Service
public class WebPageService {

    @Autowired
    private WebPageRepository webPageRepository;

    public Collection<WebPage> getAllWebPage(){
        return IteratorUtils.toList(webPageRepository.findAll().iterator());
    }

    public WebPage saveWebPage(WebPage page){
        return webPageRepository.save(page);
    }

    public Iterable<WebPage> saveWebPage(Collection<WebPage> pages){ return webPageRepository.save(pages);}

    public void saveBatchWebPage(Collection<WebPage> pages){
        int targetSize = 100;
        List<List<WebPage>> output = Lists.partition(new ArrayList<>(pages), targetSize);
        for(List<WebPage> pagesList : output){
            System.out.println("BATCH INSERTION OF ["+pagesList.size()+"]");
            webPageRepository.save(pagesList);
        }
    }

    public Collection<WebPage> findAllByClient(String client){
        return webPageRepository.findByClient(client);
    }

    public Collection<WebPage> findAllByDomaine(String domaine){
        return webPageRepository.findByDomaine(domaine);
    }

    public WebPage findOneByUrl(String url){
        return webPageRepository.findOneByUrl(url);
    }

    public static WebPage urlToWebPage(String client,String url, int status, boolean existOnSiteMap){
        try {
            URL uri = new URL(url);
            return new WebPage(client, uri.getHost(), url, new Date(), status, existOnSiteMap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

    }
}

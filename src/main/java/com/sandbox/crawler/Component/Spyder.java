package com.sandbox.crawler.Component;

import com.sandbox.crawler.Model.WebPage;
import com.sandbox.crawler.Service.WebPageService;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@Component
@Scope("prototype")
public class Spyder {

    @Autowired
    private WebPageService webPageService;

    private Set<WebPage> pageFromUrls;
    private Queue<WebPage> pageFromSiteMap;
    private Queue<WebPage> newUrl;
    private Set<WebPage> all_pages = new HashSet<>();
    private Set<WebPage> page_404 = new HashSet<>();

    public Queue<WebPage> getPageFromSiteMap() {
        return pageFromSiteMap;
    }

    public Set<WebPage> getPageFromUrls() {
        return pageFromUrls;
    }

    public Set<WebPage> getUrls_pages() {
        return all_pages;
    }

    public void setPageFromSiteMap(Queue<WebPage> pageFromSiteMap) {
        this.pageFromSiteMap = pageFromSiteMap;
        this.pageFromUrls = new HashSet<>();
        this.newUrl = new LinkedList<>();
        for(WebPage page : this.pageFromSiteMap){
            this.all_pages.add(page);
        }
    }

    private boolean shouldVisite(String url){
        for(WebPage p: this.all_pages){
            if(p.getUrl().endsWith(url))
                return false;
        }
        return true;
    }

    private boolean ignoreBadUrl(String domaine,String url){
        if(!url.startsWith("http"))
            return false;
        else if(!url.contains(domaine))
            return false;
        else if(url.endsWith(".pdf"))
            return false;
        else if(url.endsWith(".xml"))
            return false;
        else
            return true;
    }

    private String cleanUrl(String url){
        if(url.contains("?"))
            url = url.split("\\?")[0];

        //System.out.println("[" + url + "]");
        return url;
    }

    private Set<String> getUrls(String client,String url){
        Set<String> urls = new HashSet<>();
        String domaine = null;
        Connection.Response response = null;
        try {
             domaine = new URL(url).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(ignoreBadUrl(domaine, url)){
            try {
                response = Jsoup.connect(cleanUrl(url)).
                        followRedirects(true).
                        ignoreContentType(true).
                        timeout(10000).execute();
                Document doc = response.parse();

                Elements links = doc.getElementsByTag("a");
                for (Element link : links) {
                    String href = cleanUrl(link.attr("href"));
                    if (shouldVisite(href) && ignoreBadUrl(domaine, href)) {
                        WebPage newPage = null;

                        if (response.statusCode() == 404) {
                            newPage = WebPageService.urlToWebPage(client, href, 404, false);
                        } else {
                            newPage = WebPageService.urlToWebPage(client, href, 200, false);
                        }
                        this.pageFromUrls.add(newPage);
                        this.all_pages.add(newPage);
                        this.newUrl.add(newPage);
                        webPageService.saveWebPage(newPage);

                        urls.add(href);
                    }
                }
                return urls;
            } catch (HttpStatusException e){
                if(e.getStatusCode() == 404){
                    page_404.add(WebPageService.urlToWebPage(client, e.getUrl(), 404, false));
                }
                System.err.println("404 page found : " + e.getUrl());
                return urls;
            } catch (IOException e) {
                e.printStackTrace();
                return urls;
            }
        }else {
            return urls;
        }
    }

    public void getHtmlContentByClient(String client){
        getHtmlContent(webPageService.findAllByClient(client));;

    }

    public void getHtmlContentByDomaine(String domaine){
        getHtmlContent(webPageService.findAllByDomaine(domaine));
    }

    private void getHtmlContent(Collection<WebPage> pages){
        int i = 0;
        for(WebPage page : pages) {
            System.out.println("{"+i+" / "+pages.size()+"} - {"+page.getStatus()+"} - ["+page.getUrl()+"]");
            if(page.getStatus() != 404){
                try {
                    String html = Jsoup.connect(page.getUrl()).
                            followRedirects(true).
                            ignoreContentType(true).
                            timeout(10000).get().html();
                    page.setHtml(html);
                    webPageService.saveWebPage(page);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }

    public void explore(String client){

        System.out.println("1/4");
        while(!pageFromSiteMap.isEmpty()){
            WebPage page = pageFromSiteMap.poll();
            Set<String> foundUrls= getUrls(client, page.getUrl());
            System.out.println("{ALL URLS}["+all_pages.size()+"] - {EXPLORED URLS}["+pageFromUrls.size()+"] - {ITERATED URLS}["+pageFromSiteMap.size()+"] - {404 URLS}["+page_404.size()+"]");
        }
        pageFromUrls.clear();

        System.out.println("2/4");
        while (!newUrl.isEmpty()){
            WebPage page = newUrl.poll();
            Set<String> foundUrls= getUrls(client, page.getUrl());
            System.out.println("{ALL URLS}["+all_pages.size()+"] - {EXPLORED URLS}["+pageFromUrls.size()+"] - {ITERATED URLS (new URLs)}["+newUrl.size()+"] - {404 URLS}["+page_404.size()+"]");
        }
        System.out.println("3/4");
        webPageService.saveBatchWebPage(page_404);

        System.out.println("4/4");
        getHtmlContentByClient(client);
    }
}

package com.sandbox.crawler.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@Service
public class FileService {

    public Set<String> readTxtFileByUrl(String urlTxtFile){
        Set<String> lines = new LinkedHashSet<>();
        try {
            Document doc = Jsoup.connect(urlTxtFile).get();
            String pattern = "Sitemap.*\\.xml$";
            String file = doc.body().text();
            //System.out.println(file);
            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);

            // Now create matcher object.
            Matcher m = r.matcher(file);
            if (m.find( )) {
                String[] arrayValues = m.group().split(".xml");
                for(int i=0; i< arrayValues.length; i++){
                    String sitemap = arrayValues[i].split("Sitemap:")[1]+".xml";
                    System.out.println("Found value: " + sitemap);
                    lines.add(sitemap);
                }
            }else {
                System.out.println("NO MATCH");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return lines;
        }
    }

}
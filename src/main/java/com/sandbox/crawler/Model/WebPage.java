package com.sandbox.crawler.Model;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by Ouasmine on 16/05/2017.
 */
@Table(value = "webpage")
public class WebPage implements Comparable<String>{

    @PrimaryKeyColumn(name = "client",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private String client;

    @PrimaryKeyColumn(
            name = "domaine",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.ASCENDING)
    private String domaine;

    @PrimaryKeyColumn(name = "url",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.ASCENDING)
    private String url;

    @Column
    private Date datetime;

    @Column(value = "existOnSiteMap")
    private boolean existOnSiteMap;
    @Column
    private int status;
    @Column
    private String html;
    @Column
    private String title;
    @Column
    private String meta;
    @Column
    private String meta_keyword;
    @Column
    private String h1;
    @Column
    private String h2;
    @Column
    private String h3;
    @Column
    private String parent_url;

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isExistOnSiteMap() {
        return existOnSiteMap;
    }

    public void setExistOnSiteMap(boolean existOnSiteMap) {
        this.existOnSiteMap = existOnSiteMap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getParent_url() {
        return parent_url;
    }

    public void setParent_url(String parent_url) {
        this.parent_url = parent_url;
    }

    @Override
    public String toString() {
        return "WebPage{" +
                "client='" + client + '\'' +
                ", domaine='" + domaine + '\'' +
                ", url='" + url + '\'' +
                ", datetime=" + datetime +
                ", existOnSiteMap=" + existOnSiteMap +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebPage page = (WebPage) o;

        if (!url.equals(page.url)) return false;
        return datetime.equals(page.datetime);
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + datetime.hashCode();
        return result;
    }

    public WebPage(String client, String domaine, String url, Date datetime, int status, boolean existOnSiteMap) {
        this.client = client;
        this.domaine = domaine;
        this.url = url;
        this.datetime = datetime;
        this.existOnSiteMap = existOnSiteMap;
        this.status = status;
    }

    @Override
    public int compareTo(String o) {
        if(this.getUrl().equals(o))
            return 0;
        else
            return 1;
    }
}

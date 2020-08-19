package com.example.domain.dto;

import com.example.domain.News;
import com.example.domain.Site;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class NewsDTO {
    private Long id;
    private String title;
    private String link;
    private Date pubDate;
    private Site site;

    public NewsDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.link = news.getLink();
        this.pubDate = news.getPubDate();
        this.site = news.getSite();
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\" :" + "\"" +id + "\"" +
                ", \"title\" :" + "\"" +title + "\"" +
                ", \"link\" :" + "\"" +link + "\"" +
                ", \"pubDate\" :" + "\"" +pubDate.getTime() + "\"" +
                '}';
    }
}

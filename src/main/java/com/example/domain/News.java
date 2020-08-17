package com.example.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonView(Views.TitleLink.class)
    private String title;
    @JsonView(Views.TitleLink.class)
    private String link;
    //@Temporal(TemporalType.DATE)
    private Date pubDate;
    @JoinColumn(name = "site_url")
    private String siteUrl;

    public News(String title, String link, Date pubDate, String siteUrl) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.siteUrl = siteUrl;
    }

    public News() {
    }


    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
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


}

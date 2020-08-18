package com.example.domain;

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
    @Column(name = "site_url")
    private Long siteUrlId;

    public News(String title, String link, Date pubDate, Long siteUrl) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.siteUrlId = siteUrl;
    }

    public News() {
    }


    public Long getSiteUrl() {
        return siteUrlId;
    }

    public void setSiteUrl(Long siteUrl) {
        this.siteUrlId = siteUrl;
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

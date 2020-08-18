package com.example.parser;

import com.example.domain.News;
import com.example.domain.Site;
import com.example.exception.URLNotFoundInDBException;
import com.example.repos.NewsRepo;
import com.example.repos.SiteRepo;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class RSSParser extends AbstractParser implements IParser{

    NewsRepo newsRepo;
    SiteRepo siteRepo;

    @Autowired
    public RSSParser(NewsRepo newsRepo, SiteRepo siteRepo) {
        this.newsRepo = newsRepo;
        this.siteRepo = siteRepo;
    }

    @Override
    public void parse(String url) throws IOException, FeedException, URLNotFoundInDBException {
        if (!isCorrectSite(url, siteRepo)) throw new URLNotFoundInDBException("URL not found in DB");

        Site site = siteRepo.findOneByUrl(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(url)));
        List<SyndEntry> entryList = feed.getEntries();

        for (SyndEntry item : entryList){
            if(newsRepo.findOneByTitle(item.getTitle()) == null){
                News newsItem = new News(item.getTitle(), item.getLink(), item.getPublishedDate(), site);
                newsRepo.save(newsItem);
                System.out.println(item.getTitle());
            }
        }
    }
}

package com.example.parser;

import com.example.domain.News;
import com.example.repos.NewsRepo;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

@Component
public class RSSParser {
    private NewsRepo newsRepo;

    private List<News> newsItems;

    @Autowired
    public RSSParser(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    public void parseRss(String url) throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(url)));
        List<SyndEntry> entryList = feed.getEntries();
        newsItems = newsRepo.findAllBysiteUrl(url);

        Iterator<SyndEntry> iterator = entryList.iterator();
        if(newsItems.size() == 0){
            for(SyndEntry syndEntry : entryList){
                newsRepo.save(new News(syndEntry.getTitle(), syndEntry.getLink(), syndEntry.getPublishedDate(), url));
            }
        }
        else {
            newsItems = newsRepo.findAllBysiteUrl(url);
            while (iterator.hasNext()) {
                SyndEntry item = iterator.next();
                if (item.getPublishedDate().getTime() > newsItems.get(newsItems.size() - 1).getPubDate().getTime()) {
                    News newsItem = new News(item.getTitle(), item.getLink(), item.getPublishedDate(), url);
                    newsItems.add(newsItem);
                    newsRepo.save(newsItem);
                }
            }
        }
    }
}

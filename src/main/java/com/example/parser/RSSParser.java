package com.example.parser;

import com.example.domain.News;
import com.example.domain.Site;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class RSSParser implements IParser{
    private Site site;

    public RSSParser(Site site) {
        this.site = site;
    }

    public RSSParser(){}


    @Override
    public List<News> parse() throws IOException, FeedException {
        List<News> newsList = new ArrayList<>();
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(site.getUrl())));
        List<SyndEntry> entryList = feed.getEntries();

        for(SyndEntry entry : entryList){
            News newsItem = new News(entry.getTitle(), entry.getLink(), entry.getPublishedDate(), site);
            newsList.add(newsItem);
        }

        return newsList;
    }
}

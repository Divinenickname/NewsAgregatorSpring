package com.example.parser;

import com.example.repos.NewsRepo;
import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {

    private NewsRepo newsRepo;
    private RSSParser parser;

    @Autowired
    public ScheduledTasks(NewsRepo newsRepo){
        this.newsRepo = newsRepo;
        parser = new RSSParser(newsRepo);
    }

    @Scheduled(fixedRate = 5000)
    public void RSSScheduler() throws IOException, FeedException {
        parser.parseRss("https://news.yandex.ru/auto.rss");
        parser.parseRss("https://news.yandex.ru/politics.rss");
    }
}

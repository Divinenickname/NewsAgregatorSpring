package com.example.parser;

import com.example.exception.URLNotFoundInDBException;
import com.example.repos.NewsRepo;
import com.example.repos.SiteRepo;
import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {



    private final RSSParser parser;
    private final VCRUParser vcruParser;

    private NewsRepo newsRepo;
    private SiteRepo siteRepo;

    @Autowired
    public ScheduledTasks(NewsRepo newsRepo, SiteRepo siteRepo){
        parser = new RSSParser(newsRepo, siteRepo);
        vcruParser = new VCRUParser(newsRepo, siteRepo);
    }

    @Scheduled(fixedRate = 10000)
    public void RSSScheduler() throws IOException, FeedException, URLNotFoundInDBException {
        parser.parse("https://news.yandex.ru/auto.rss");
        vcruParser.parse("https://vc.ru");
    }
}

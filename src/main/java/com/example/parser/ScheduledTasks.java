package com.example.parser;

import com.example.repos.SiteRepo;
import com.example.services.NewsService;
import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {

    private final RSSParser parser;
    private final VCRUParser vcruParser;
    @Autowired
    private final NewsService newsService;
    private SiteRepo siteRepo;

    public ScheduledTasks(SiteRepo siteRepo, NewsService newsService){
        this.siteRepo = siteRepo;
        this.newsService = newsService;

        parser = new RSSParser(siteRepo.findOneByUrl("https://news.yandex.ru/auto.rss"));
        vcruParser = new VCRUParser(siteRepo.findOneByUrl("https://vc.ru"));
    }

    @Scheduled(fixedRate = 10000)
    public void RSSScheduler() throws IOException, FeedException{
        newsService.saveNewsList(newsService.removeDublicates(parser.parse()));
        newsService.saveNewsList(newsService.removeDublicates(vcruParser.parse()));

    }
}

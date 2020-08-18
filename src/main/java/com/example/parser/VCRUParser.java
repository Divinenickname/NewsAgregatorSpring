package com.example.parser;

import com.example.domain.News;
import com.example.domain.Site;
import com.example.exception.URLNotFoundInDBException;
import com.example.repos.NewsRepo;
import com.example.repos.SiteRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class VCRUParser extends AbstractParser implements IParser{

    private final NewsRepo newsRepo;
    private final SiteRepo siteRepo;

    @Autowired
    public VCRUParser(NewsRepo newsRepo, SiteRepo siteRepo) {
        this.newsRepo = newsRepo;
        this.siteRepo = siteRepo;
    }

    @Override
    public void parse(String url) throws IOException, URLNotFoundInDBException {
        if (!isCorrectSite(url, siteRepo)) throw new URLNotFoundInDBException(url+ " URL not found in DB");

        Document doc = Jsoup.connect(url).get();

        Elements urls = doc.select("div.feed__item > div.content-feed > a");
        Elements titles = doc.select("div.feed__item > div.content-feed > div.content-header > h2.content-header__title");
        Elements dates = doc.select("div.feed__item > div.content-feed > div.content-header > div.content-header__info > div.content-header__left > div.content-header-number > a > time.time");
        for (int i = 0; i < urls.size(); i++) {
            String title = titles.get(i).text();
            String itemLink = urls.get(i).attr("href");
            Timestamp timestamp = new Timestamp(TimeUnit.SECONDS.toMillis(Long.parseLong(dates.get(i).attr("data-date"))));
            Date pubDate = new Date(timestamp.getTime());

            int count = (int) newsRepo.findUniqueByURL(itemLink, PageRequest.of(1, 1)).getTotalElements();
            if( count== 0){
                Site site = siteRepo.findOneByUrl(url);
                News item = new News(title, itemLink, pubDate, site);
                System.out.println(item.getTitle());
                newsRepo.save(item);
            }

        }
    }

}

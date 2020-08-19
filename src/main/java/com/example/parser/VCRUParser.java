package com.example.parser;

import com.example.domain.News;
import com.example.domain.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class VCRUParser implements IParser{
    private static final String titlePath = "div.content-feed > div.content-header > h2.content-header__title";
    private static final String linkPath = "div.content-feed > a";
    private static final String timestampPath = "div.content-feed > div.content-header > div.content-header__info > div.content-header__left > div.content-header-number > a > time.time";

    Logger logger = LoggerFactory.getLogger(VCRUParser.class);
    private Site site;

    public VCRUParser(Site site){
        this.site = site;
    }

    public VCRUParser() {
    }

    @Override
    public List<News> parse() throws IOException {
        List<News> newsList = new ArrayList<>();

        Document doc = Jsoup.connect(site.getUrl()).get();
        Elements node = doc.select("div.feed__item");

        for (Element element : node) {
            String title;
            String link;
            String tmpDate;
            Date pubDate;

            Elements child = element.children();
            try{
                title = child.select(titlePath).get(0).text();
                link = child.select(linkPath).get(0).attr("href");
                tmpDate = child.select(timestampPath).get(0).attr("data-date");
                pubDate = new Date(new Timestamp(TimeUnit.SECONDS.toMillis(Long.parseLong(tmpDate))).getTime());

                News newsItem = new News(title, link, pubDate, site);
                newsList.add(newsItem);
            }
            catch (Exception ex){
                logger.info("News has no any title, it was missed!");
            }
        }

        return newsList;
    }

}

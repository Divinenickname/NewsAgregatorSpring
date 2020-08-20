package com.example.services;

import com.example.domain.News;
import com.example.repos.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    private NewsRepo newsRepo;

    @Autowired
    public NewsServiceImpl(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override
    public List<News> removeDublicates(List<News> newsList) {
        Iterator<News> iterator = newsList.iterator();
        while (iterator.hasNext()){
            News newsItem = iterator.next();
            News tmpNews = newsRepo.findOneByTitle(newsItem.getTitle());

            if(newsItem.equals(tmpNews)) iterator.remove();
        }

        return newsList;
    }

    @Override
    public void saveNewsList(List<News> newsList) {
        for(News item : newsList){
            newsRepo.save(item);
        }
    }

    @Override
    public List<News> findAll() {
        List<News> dto = new ArrayList<>();
        Iterable<News> iterable = newsRepo.findAll();

        for(News item : iterable){
            dto.add(item);
        }

        return dto;
    }


}

package com.example.services;

import com.example.domain.News;

import java.util.List;

public interface NewsService {
    List<News> removeDublicates(List<News> newsList);
    void saveNewsList(List<News> newsList);
}

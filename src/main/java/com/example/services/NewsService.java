package com.example.services;

import com.example.domain.News;
import com.example.domain.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    List<News> removeDublicates(List<News> newsList);
    void saveNewsList(List<News> newsList);
    List<NewsDTO> findAll();
}

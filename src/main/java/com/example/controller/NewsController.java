package com.example.controller;

import com.example.domain.News;
import com.example.domain.Views;
import com.example.domain.dto.NewsDTO;
import com.example.domain.dto.NewsDTOMapper;
import com.example.repos.NewsRepo;
import com.example.services.NewsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    NewsDTOMapper mapper = new NewsDTOMapper();
    @Autowired
    NewsRepo newsRepo;
    @Autowired
    NewsService newsService;

    public NewsController(NewsRepo newsRepo, NewsService newsService) {
        this.newsRepo = newsRepo;
        this.newsService = newsService;
    }

    public NewsController() {
    }

    @GetMapping(value = "/news")
    @ResponseBody
    public String StringResponse(){
        List<NewsDTO> list = mapper.listToDTO(newsService.findAll());
        return list.toString();
    }

    @GetMapping(value = "/")
    @ResponseBody
    @JsonView(Views.TitleLink.class)
    public Map<String, List<News>> mainRepo(){
        Map<String, List<News>> map = new HashMap<>();
        map.put("items", new ArrayList<>(newsRepo.getAll()));
        return map;
    }

    @GetMapping(value = "/get")
    @ResponseBody
    @JsonView(Views.TitleLink.class)
    public List<NewsDTO> getNews(@RequestParam(value = "str") String str){

        return mapper.listToDTO(newsRepo.findByTitleLike(str));
    }
}

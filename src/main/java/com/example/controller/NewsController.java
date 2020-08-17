package com.example.controller;

import com.example.domain.News;
import com.example.domain.Views;
import com.example.repos.NewsRepo;
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
    @Autowired
    NewsRepo newsRepo;

    public NewsController() {
    }

    @GetMapping(value = "/getnews")
    @ResponseBody
    public String StringResponse(@RequestParam(value = "url") String url){
        return url;
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
    public List<News> getNews(@RequestParam(value = "str") String str){
        return newsRepo.findByTitleLike(str);
    }
}

package com.example.domain.dto;

import com.example.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDTOMapper implements INewsDTOMapper{
    @Override
    public NewsDTO toDTO(News news){
        return new NewsDTO(news);
    }

    @Override
    public List<NewsDTO> listToDTO(List<News> newsList){
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for(News item: newsList){
            newsDTOList.add(new NewsDTO(item));
        }

        return newsDTOList;
    }


}

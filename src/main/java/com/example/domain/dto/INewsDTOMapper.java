package com.example.domain.dto;

import com.example.domain.News;

import java.util.List;

/**
 *
 */
public interface INewsDTOMapper {
    /**
     *
     * @param newsList pure entities list from DB.
     * @return list of DTO news objects.
     */
    List<NewsDTO> listToDTO(List<News> newsList);

    /**
     *
     * @param news one entity from DB.
     * @return DTO news object
     */
    NewsDTO toDTO(News news);
}

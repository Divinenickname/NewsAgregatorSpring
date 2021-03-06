package com.example.repos;

import com.example.domain.News;
import com.example.domain.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends CrudRepository<News, Integer> {
    @Query(value = "SELECT * FROM news order by pub_date asc", nativeQuery = true)
    List<News> getAll();

    @Query("select count(n) from News n where n.link = :siteUrl")
    Page<News> findUniqueByURL(@Param("siteUrl") String siteUrl, Pageable pageable);

    @Query("from News n where n.title like %:title%")
    List<News> findByTitleLike(@Param("title") String title);

    News findOneByLink(String link);
    News findOneByTitle(String title);
    Page<NewsDTO> findAll(Pageable pageable);
}

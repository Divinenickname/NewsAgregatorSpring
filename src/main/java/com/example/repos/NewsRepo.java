package com.example.repos;

import com.example.domain.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface NewsRepo extends CrudRepository<News, Integer> {
    @Query(value = "SELECT * FROM news order by pub_date asc", nativeQuery = true)
    List<News> getAll();

    @Query("from News n where n.siteUrl = :siteUrl")
    List<News> findAllBysiteUrl(@Param("siteUrl") String siteUrl);

    @Query("from News n where n.title like %:title%")
    List<News> findByTitleLike(@Param("title") String title);
}

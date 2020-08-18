package com.example.repos;

import com.example.domain.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepo extends CrudRepository<Site, Long> {

    Site findOneByUrl(String url);

}

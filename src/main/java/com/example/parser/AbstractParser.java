package com.example.parser;

import com.example.domain.Site;
import com.example.repos.SiteRepo;

public class AbstractParser {
    boolean isCorrectSite(String url, SiteRepo siteRepo){
        for (Site site : siteRepo.findAll()){
            if(site.getUrl().equals(url)) return true;
        }

        return false;
    }
}

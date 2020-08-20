package com.example.parser;

import com.example.domain.News;

import java.io.IOException;
import java.util.List;

public interface IParser {
    /**
     * Just parse and return list of news
     * @return List of parsed news
     * @throws IOException can be thrown by url connection
     */
    List<News> parse() throws IOException;

}

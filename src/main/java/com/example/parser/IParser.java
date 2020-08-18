package com.example.parser;

import com.example.domain.News;
import com.rometools.rome.io.FeedException;

import java.io.IOException;
import java.util.List;

public interface IParser {
    List<News> parse() throws IOException, FeedException;

}

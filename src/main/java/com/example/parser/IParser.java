package com.example.parser;

import com.example.exception.URLNotFoundInDBException;
import com.rometools.rome.io.FeedException;

import java.io.IOException;

public interface IParser {
    void parse(String url) throws IOException, FeedException, URLNotFoundInDBException;
}

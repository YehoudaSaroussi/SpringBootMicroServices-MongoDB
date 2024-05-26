package com.example.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.news.*;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataFetcher implements CommandLineRunner {

    @Autowired
    private DataService dataService;

    @Autowired
    private NewsArticleRepository newsArticleRepository;

    @Override
    public void run(String... args) throws Exception {
        fetchAndSaveNews();
    }

    private void fetchAndSaveNews() {
        String url = "https://gnews.io/api/v4/top-headlines?category=" + "general";
        log.info("Fetching news data for : " + url);
        dataService.fetchAndSaveData(url, NewsArticle.class, newsArticleRepository);
    }

}

package com.example.data;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/news")
public class DataController {

    private final NewsArticleRepository newsArticleRepository;

    public DataController(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    @GetMapping("/top-headlines")
    public List<NewsArticle> getNewsArticles() {
        log.info("Getting news articles from DB");
        return newsArticleRepository.findAll();
    }

}

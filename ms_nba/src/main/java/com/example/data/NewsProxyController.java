package com.example.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.resources.*;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RestController
@Slf4j
// @Log4j2
@RequestMapping("/news")
public class NewsProxyController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${news.service.url}")
    private String newsServiceUrl;

    @GetMapping("/**")
    public List<NewsArticle> forwardRequestToNews(HttpServletRequest request) {

        String originalUri = request.getRequestURI();

        String modifiedUri = originalUri.substring("/news".length());

        String url = newsServiceUrl + modifiedUri;

        ResponseEntity<NewsArticle[]> response = restTemplate.getForEntity(url, NewsArticle[].class);

        log.info("Response from news service");
        return Arrays.asList(response.getBody());
    }
}

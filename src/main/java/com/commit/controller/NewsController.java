package com.commit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.commit.model.NewsDto;
import com.commit.service.NewsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable(name = "id") Integer id) {
        NewsDto news = newsService.getNewsById(id);
        
        List<NewsDto> popularNews = newsService.getPopularNews();
        List<NewsDto> latestNews = newsService.getLatestNews();
        List<NewsDto> reletedNews = newsService.getRelatedNews(news.getCategory());

        Map<String, Object> response = new HashMap<>();
        response.put("news", news);
        response.put("popularNews", popularNews);
        response.put("latestNews", latestNews);
        response.put("relatedNews", reletedNews);
        System.out.println("reletedNews log : " + reletedNews);

        return ResponseEntity.ok(response);
    }
}

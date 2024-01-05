package com.commit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.commit.entity.News;
import com.commit.model.NewsDto;
import com.commit.service.NewsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsService newsService;
    
    // 페이지네이션 컨트롤러 
    @GetMapping("/news")
    public Page<News> getNews(@PageableDefault(size=10, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
    	Page<News> result = newsService.getPages(pageable);
    	return result;
    }
    
    @GetMapping("/news/category/{category}")
    public ResponseEntity<Map<String, Object>> getNewsByCategory(@PathVariable(name = "category") String category) {
        List<NewsDto> newsList = newsService.getNewsByCategory(category);
        NewsDto topNews = newsService.getTopNewsByCategory(category);
        List<NewsDto> popularNews = newsService.getPopularNews();
        List<NewsDto> latestNews = newsService.getLatestNews();

        Map<String, Object> response = new HashMap<>();
        response.put("listNews", newsList);
        response.put("topNews", topNews);
        response.put("popularNews", popularNews);
        response.put("latestNews", latestNews);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/news/article/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable(name = "id") Integer id) {
        NewsDto newsView = newsService.getNewsById(id);
        List<NewsDto> popularNews = newsService.getPopularNews();
        List<NewsDto> latestNews = newsService.getLatestNews();
        List<NewsDto> reletedNews = newsService.getRelatedNews(newsView.getCategory());

        Map<String, Object> response = new HashMap<>();
        response.put("news", newsView);
        response.put("popularNews", popularNews);
        response.put("latestNews", latestNews);
        response.put("relatedNews", reletedNews);

        return ResponseEntity.ok(response);
    }
}

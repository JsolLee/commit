package com.commit.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commit.entity.News;
import com.commit.model.NewsDto;
import com.commit.service.NewsService;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsService newsService;
    
    @GetMapping
    public List<News> getMainNews() {
        List<News> mainNewsList = new ArrayList<>();
        
        // getFirstNews() 메서드 호출
        List<News> firstNewsList = newsService.getFirstNews();
        mainNewsList.addAll(firstNewsList);
        
//        // getFourFiveNews() 메서드 호출
//        List<News> fourFiveNewsList = newsService.getFourFiveNews();
//        mainNewsList.addAll(fourFiveNewsList);
//        
//        // getSixSevenNews() 메서드 호출
//        List<News> sixSevenNewsList = newsService.getSixSevenNews();
//        mainNewsList.addAll(sixSevenNewsList);
        
        // getCategoryNews() 메서드 호출
        List<News> categoryNewsList = newsService.getCategoryNews();
        mainNewsList.addAll(categoryNewsList);
        
        return mainNewsList;
    }
    
    // 페이지네이션 컨트롤러 
    @GetMapping("/catagory/**")
    public Page<News> getNews(@PageableDefault(size=10, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
    	Page<News> result = newsService.getPages(pageable);
    	return result;
    }
    
    // 뉴스 카테고리 가져오기
    @GetMapping("/category/{category}")
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

    // 뉴스 기사 가져오기
    @GetMapping("/article/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable(name = "id") Integer id) {
        NewsDto newsView = newsService.getNewsById(id);
        List<NewsDto> popularNews = newsService.getPopularNews();
        List<NewsDto> latestNews = newsService.getLatestNews();
        List<NewsDto> reletedNews = newsService.getRelatedNews(newsView.getCategory());
        newsService.incrementNewsView(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("news", newsView);
        response.put("popularNews", popularNews);
        response.put("latestNews", latestNews);
        response.put("relatedNews", reletedNews);

        return ResponseEntity.ok(response);
    }
}

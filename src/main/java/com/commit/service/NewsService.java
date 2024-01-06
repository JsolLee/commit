package com.commit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.commit.entity.News;
import com.commit.model.NewsDto;
import com.commit.repository.NewsDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;
    
    // 페이지네이션
    Pageable pageable = PageRequest.of(0, 10);
	public Page<News> getPages(Pageable pageable) {
		return newsDao.findAll(pageable);
	}

	// NewsList
	// 카테고리별로 가져오기
    public List<NewsDto> getNewsByCategory(String category) {
    	// 1. Dao에서 카테고리 spl 가져오기
        return newsDao.findByCategoryOrderByCreateDateDesc(category).stream()
                // 2. map으로 NewsDto의 convertToDto으로 전환해주기
        		.map(NewsDto::convertToDto)
        		// 3. collect으로 리스트로 보내기
                .collect(Collectors.toList());
    }
    
    // 카테고리 메인뉴스 가져오기
    public NewsDto getTopNewsByCategory(String category) {
    	// 1. 
        Page<News> newsPage = newsDao.findTopByCategoryOrderByLikecountDescViewcountDesc(category, PageRequest.of(0, 1));
        return newsPage.hasContent() ? NewsDto.convertToDto(newsPage.getContent().get(0)) : null;
    }
    
    //NewsView
    // 뉴스 가져오기 : id로 가져오기
    public NewsDto getNewsById(Integer id) {
    	// 1. id로 DB 조회
        Optional<News> news = newsDao.findById(id);
        // 2. DB 조회 갯수 null check
        if(news.isPresent()) {return NewsDto.convertToDto(news.get());}
        // 3. news를 DTO로 변경하 리턴
        return null;
    }
    
    // 인기 뉴스 가져오기
    public List<NewsDto> getPopularNews() {
        return newsDao.findTop6ByOrderByViewcountDesc().stream()
                .map(NewsDto::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 최신 뉴스 목록 가져오기
    public List<NewsDto> getLatestNews() {
        return newsDao.findTop6ByOrderByCreateDateDesc().stream()
                .map(NewsDto::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 관련 뉴스 가져오기
    public List<NewsDto> getRelatedNews(String category) {
        return newsDao.findTop6ByCategoryOrderByCreateDateDesc(category).stream()
                .map(NewsDto::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 뉴스 조회수 가져오기
    public void incrementNewsView(Integer id) {
        newsDao.incrementViewCount(id);
    }
}

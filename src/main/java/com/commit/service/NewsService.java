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
        return newsDao.findByCategoryOrderByCreateDateDesc(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 카테고리 메인뉴스 가져오기
    public NewsDto getTopLikedAndViewedNews() {
        News news = newsDao.findFirstByOrderByLikecountDescViewcountDesc();
        return news != null ? convertToDto(news) : null;
    }
	
    //NewsView
    // 뉴스 가져오기 : id로 가져오기
    public NewsDto getNewsById(Integer id) {
        Optional<News> news = newsDao.findById(id);
        if(news.isPresent()) {return convertToDto(news.get());}
        return null;
    }
    
    // 인기 뉴스 가져오기
    public List<NewsDto> getPopularNews() {
        return newsDao.findTop6ByOrderByViewcountDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 최신 뉴스 목록 가져오기
    public List<NewsDto> getLatestNews() {
        return newsDao.findTop6ByOrderByCreateDateDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 관련 뉴스 가져오기
    public List<NewsDto> getRelatedNews(String category) {
        return newsDao.findTop6ByCategoryOrderByCreateDateDesc(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // json type으로 컨버팅
    private NewsDto convertToDto(News news) {
    	return NewsDto.builder()
                .id(news.getId())
                .category(news.getCategory())
                .title(news.getTitle())
                .subtitle(news.getSubtitle())
                .content(news.getContent())
                .origin(news.getOrigin())
                .image(news.getImage())
                .writer(news.getWriter())
                .viewcount(news.getViewcount())
                .likecount(news.getLikecount())
                .originDate(news.getOriginDate())
                .createDate(news.getCreateDate())
                .build();
    }
}

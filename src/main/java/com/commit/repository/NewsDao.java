package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {
	
	// 인기뉴스 : 높은 순으로 6개 가져오기
	List<News> findTop6ByOrderByViewcountDesc();
	
	// 최신뉴스 : 가장 최신순으로 6개 가져오기
	List<News> findTop6ByOrderByCreateDateDesc();
	
	// 관련뉴스 : 같은 카테고리에서 최신순으로 6개 가져오기
    List<News> findTop6ByCategoryOrderByCreateDateDesc(String category);
}
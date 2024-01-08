package com.commit.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commit.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {
	
	// NewsList
	// 카테고리 : 카테고리 별로 나누어서 가져오기
	List<News> findByCategoryOrderByCreateDateDesc(String category);
	
	// 카테고리 : 각 카테고리 별 메인뉴스 가져오기
    @Query("SELECT n FROM News n WHERE n.category = :category ORDER BY n.likecount DESC, n.viewcount DESC")
    Page<News> findTopByCategoryOrderByLikecountDescViewcountDesc(@Param("category") String category, Pageable pageable);
	
	// NewsView
	// 인기뉴스 : 조회수가 높은 순으로 6개 가져오기
	List<News> findTop6ByOrderByViewcountDesc();
	
	// 최신뉴스 : 가장 최신순으로 6개 가져오기
	List<News> findTop6ByOrderByCreateDateDesc();
	
	// 관련뉴스 : 같은 카테고리에서 최신순으로 6개 가져오기
    List<News> findTop6ByCategoryOrderByCreateDateDesc(String category);
    
    // 뉴스 조회수 증가
    @Transactional
    @Modifying
    @Query("UPDATE News n SET n.viewcount = n.viewcount + 1 WHERE n.id = :id")
    int incrementViewCount(@Param("id") Integer id);
    
    // 뉴스 좋아요 수 증가
    @Transactional
    @Modifying
    @Query("UPDATE News n SET n.likecount = n.likecount + 1 WHERE n.id = :id")
    int incrementLikeCount(@Param("id") Integer id);
    
    // NewsMain
    // 메인 중앙 뉴스 : 인기뉴스 가져오기
    List<News> findByOrderByViewcountDesc();
    
    // 인기순으로 3개 가져오기
    List<News> findTop7ByOrderByViewcountDesc();
    
    // 같은 카테고리에서 3개 가져오기
    List<News> findTop3ByCategoryOrderByCreateDateDesc(String category);
}


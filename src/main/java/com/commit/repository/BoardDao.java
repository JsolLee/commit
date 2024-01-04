package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.Board;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer>{
    
    // 카테고리 검색 후 최신순으로 6개
    List<Board> findTop6ByCategoryOrderByCreateDateDesc(String category);
}
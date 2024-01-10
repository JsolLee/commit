package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.NewsLike;

@Repository
public interface NewsLikeDao extends JpaRepository<NewsLike, Integer> {
	// 뉴스 좋아요
	List<NewsLike> findByMembers_Id(Integer membersId);
}

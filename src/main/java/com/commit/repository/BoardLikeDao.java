package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.BoardLike;

@Repository
public interface BoardLikeDao extends JpaRepository<BoardLike, Integer> {
	// 뉴스 좋아요
	List<BoardLike> findByMembers_Id(Integer membersId);
}

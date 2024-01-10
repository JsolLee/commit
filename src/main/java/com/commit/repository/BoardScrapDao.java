package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.BoardScrap;

@Repository
public interface BoardScrapDao extends JpaRepository<BoardScrap, Integer> {
	// 채용 스크랩
	List<BoardScrap> findByMembers_Id(Integer membersId);
}

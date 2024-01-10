package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.NewsScrap;

@Repository
public interface NewsScrapDao extends JpaRepository<NewsScrap, Integer> {
	// 채용 스크랩
	List<NewsScrap> findByMembers_Id(Integer membersId);
}

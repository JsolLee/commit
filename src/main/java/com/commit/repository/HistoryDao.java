package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commit.entity.LoginHistory;

public interface HistoryDao extends JpaRepository<LoginHistory, Integer>{
	List<LoginHistory> findLoginHistoriesByMemberIdOrderByCreateDateDesc(String memberID);
}

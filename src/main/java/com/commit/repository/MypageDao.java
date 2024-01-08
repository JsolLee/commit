package com.commit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.LoginHistory;
import com.commit.entity.Members;

@Repository
public interface MypageDao extends JpaRepository<Members, Integer> {
	
	   Optional<Members> findByMemberId(String memberId);
	   Optional<Members> findAllByMemberId(String memberId);
	   
}

package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.Board;

@Repository
public interface MypageBoardDao extends JpaRepository<Board, Integer>{	
	// 마이페이지 -> 내가 쓴 글(커뮤니티)
//	List<Board> findByMember_Id(Integer membersId);
}
package com.commit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.Members;

@Repository
public interface MembersDao extends JpaRepository<Members, Integer>{
	   Optional<Members> findByMemberIdAndMemberPw(String memberId, String memberPw);
	   
	   public Members findByNickName(String nickName);

	   // 멤버 아이디가 존재하는지
	   boolean existsByMemberId(String memberId);
	   
	   // 닉네임이 존재하는지
	   boolean existsByNickName(String nickName);
	   
	   // 로그인
	   Optional<Members> findByMemberId(String memberId);

}

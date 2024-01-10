package com.commit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.Members;

@Repository
public interface MypageDao extends JpaRepository<Members, Integer> {
	// 마이페이지 메인
	Optional<Members> findAllByMemberId(String memberId);
	Optional<Members> findByMemberId(String memberId);
	Optional<Members> findByNickName(String nickName);

	// 스크랩에서 사용
	Optional<Members> findByIdAndMemberId(Integer membersId, String memberId);

}

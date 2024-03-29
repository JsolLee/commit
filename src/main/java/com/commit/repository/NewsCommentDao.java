package com.commit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.NewsComment;
import com.commit.entity.NewsLike;

@Repository
public interface NewsCommentDao extends JpaRepository<NewsComment, Integer> {

	// 뉴스 ID에 따른 댓글 목록 조회
	List<NewsComment> findByNewsId(Integer newsId);

	// 특정 댓글에 대한 대댓글 목록 조회
	List<NewsComment> findByParentId(Integer parentId);

	// 마이페이지 -> 내가 쓴 댓글(뉴스)
	List<NewsComment> findByMember_Id(Integer membersId);
}
package com.commit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commit.entity.Board;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer>{	
	
	// 카테고리 검색 후 최신순으로 6개
    List<Board> findTop6ByCategoryOrderByCreateDateDesc(String category);
    
    // 최신순으로 6개
    List<Board> findTop6ByOrderByCreateDateDesc();
	
	//게시판 글 검색 메서드
	 @Query("SELECT b FROM Board b WHERE " +
	            "(:option = '전체' AND (b.title LIKE %:keyword% OR b.content LIKE %:keyword%)) OR " +
	            "(:option = '제목' AND b.title LIKE %:keyword%) OR " +
	            "(:option = '내용' AND b.content LIKE %:keyword%)")
	    List<Board> searchBoard(@Param("keyword") String keyword, @Param("option") String option);
	 
	//카테고리 별 조회 메서드
//	 Page<Board> findByCategory(String category, Pageable pageable);
	 
	//카테고리별 조회 메서드
	 @Query("SELECT b FROM Board b WHERE b.category = :category AND b.deleteYN = 'N'")
	    Page<Board> findByCategory(@Param("category") String category, Pageable pageable);
	 
	//목록에서 DeleteYN이 N인 값만 조회
	  @Query("SELECT b FROM Board b WHERE b.deleteYN = 'N'")
	    Page<Board> findDeletedBoards(Pageable pageable);
	  
	 //상세 페이지에서 DeleteYn이 n인 값만 조회
	  @Query("SELECT b FROM Board b WHERE b.id = :id AND b.deleteYN = :deleteYN")
	    Optional<Board> findByIdAndDeleteYN(@Param("id") Integer id, @Param("deleteYN") String deleteYN);

}
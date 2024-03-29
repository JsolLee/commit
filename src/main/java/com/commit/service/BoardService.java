package com.commit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commit.entity.Board;
import com.commit.entity.BoardLike;
import com.commit.entity.Members;
import com.commit.model.BoardDto;
import com.commit.repository.BoardDao;
import com.commit.repository.MembersDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class BoardService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private final BoardDao boardDao;
	@Autowired
	private final MembersDao membersDao;
	    
	    public BoardService(BoardDao boardDao, MembersDao membersDao) {
	        this.boardDao = boardDao;
	        this.membersDao = membersDao;
	    }
	    
	    public List<Board> getBoard() {
			   // 전체글은 createDate가 최신인 순으로 6개
			   // List<Board> AllList = boardDao.findTop6ByOrderByCreateDateDesc();
			   
			   // 나머지는 카테리고별 최신 순으로 6개 설정
			   List<Board> newStartList = boardDao.findTop6ByCategoryOrderByCreateDateDesc("newstart");
			   List<Board> resumeList = boardDao.findTop6ByCategoryOrderByCreateDateDesc("resume");
			   List<Board> reviewList = boardDao.findTop6ByCategoryOrderByCreateDateDesc("review");
			   List<Board> recruitList = boardDao.findTop6ByCategoryOrderByCreateDateDesc("recruit");
			   List<Board> interViewList = boardDao.findTop6ByCategoryOrderByCreateDateDesc("interview");
			   
			   List<Board> allBoardList = new ArrayList<>();
			   // allBoardList.addAll(AllList);
			   allBoardList.addAll(newStartList);
			   allBoardList.addAll(resumeList);
			   allBoardList.addAll(reviewList);
			   allBoardList.addAll(recruitList);
			   allBoardList.addAll(interViewList);
			   
			   return allBoardList;
		   }
		   
		   // 비상용
		   private BoardDto convertToDto(Board board) {
			   return BoardDto.builder()
					   .boardFIleId(board.getBoardFIleId())
					   .id(board.getId())
					   .membersId(board.getMembersId())
					   .likecount(board.getLikecount())
					   .viewcount(board.getViewcount())
					   .createDate(board.getCreateDate())
					   .updateDate(board.getUpdateDate())
					   .category(board.getCategory())
					   .content(board.getContent())
					   .deleteYN(board.getDeleteYN())
					   .title(board.getTitle())
					   .build();
		   }
		   
       // 멤버의 닉네임을 가져오는 메서드
	   private String getMemberNickname(Integer membersId) {
		    if (membersId == null) {
		        return ""; 
		    }
		    Optional<Members> members = membersDao.findById(membersId);
		    return members.map(Members::getNickName).orElse(null);
		}
		   
	   //게시판 글 작성 메서드
	   @Transactional
	   public Integer boardWrite(BoardDto boardDto) {
	       return boardDao.save(boardDto.toEntity()).getId();
	   }
    
	    // 게시판 글 목록 조회 메서드
	    @Transactional
	    public Page<BoardDto> getBoardAllList(Pageable pageable){
	        Page<Board> boards = boardDao.findDeletedBoards(pageable);
	        Page<BoardDto> boardDtoList = boards.map(board -> BoardDto.builder()
	                .id(board.getId())
	                .title(board.getTitle())
	                .content(board.getContent())
	                .membersId(board.getMembersId())
	                .createDate(board.getCreateDate())
	                .viewcount(board.getViewcount())
	                .likecount(board.getLikecount())
	                .category(board.getCategory())
	                .build());
	        
	        // 각 BoardDto에 대해 닉네임 추가
	        boardDtoList.forEach(boardDto -> boardDto.setNickname(getMemberNickname(boardDto.getMembersId())));
	        return boardDtoList;           
	    }
	    
	    //카테고리 별 조회 메서드
	    @Transactional
	    public Page<BoardDto> getBoardList(Pageable pageable, String category) {
	        Page<Board> boards;
	        
	        if (category != null && !category.isEmpty()) {
	            // 카테고리가 지정된 경우 해당 카테고리의 글 목록 조회
	            boards = boardDao.findByCategory(category, pageable);
	        } else {
	            // 카테고리가 지정되지 않은 경우 전체 글 목록 조회
	            boards = boardDao.findAll(pageable);
	        }

	        Page<BoardDto> boardDtoList = boards.map(board -> BoardDto.builder()
	                .id(board.getId())
	                .title(board.getTitle())
	                .content(board.getContent())
	                .membersId(board.getMembersId())
	                .createDate(board.getCreateDate())
	                .viewcount(board.getViewcount())
	                .likecount(board.getLikecount())
	                .category(board.getCategory())
	                .build());
	        
	        // 각 BoardDto에 대해 닉네임 추가
	        boardDtoList.forEach(boardDto -> boardDto.setNickname(getMemberNickname(boardDto.getMembersId())));
	        return boardDtoList;
	    }
	    
	   
	  //게시판 글 상세 조회 메서드
	  @Transactional
	  public BoardDto getBoard(Integer id) {
		  Optional<Board> boardWrapper = boardDao.findByIdAndDeleteYN(id, "N");
		  
		  Board board = boardWrapper.get();
		  
		  	// 조회수 증가 메서드
		    board.setViewcount(board.getViewcount() + 1);
		    boardDao.save(board);
		  
		  BoardDto boardDto = BoardDto.builder()
				  .id(board.getId())
				  .title(board.getTitle())
				  .content(board.getContent())
				  .category(board.getCategory())
				  .membersId(board.getMembersId())
				  .createDate(board.getCreateDate())
				  .updateDate(board.getUpdateDate())
				  .viewcount(board.getViewcount())
				  .likecount(board.getLikecount())
				  .deleteYN(board.getDeleteYN())
				  .build();
		  
		  // 닉네임 추가
		  boardDto.setNickname(getMemberNickname(board.getMembersId()));
		  
		  return boardDto;
	  }
	    	   
	  
	  //게시판 글 삭제 메서드
//	  @Transactional
//	  public void boarddelete(Integer id) {
//		  boardDao.deleteById(id);
//	  }
	  
	  //게시판 글 삭제 메서드 (YN으로 변경)
	  public BoardDto boarddelete(Integer id) {
		    Optional<Board> board = boardDao.findById(id);

		    if (board.isPresent()) {
		        Board deletedBoard = board.get();
		        deletedBoard.setDeleteYN("Y"); // deleteYN을 'Y'로 설정

		        boardDao.save(deletedBoard); // 업데이트된 포스트를 저장

		        // 삭제된 게시글 정보를 BoardDto로 변환하여 반환
		        return convertToDto(deletedBoard);
		    } else {
		        // 게시글이 존재하지 않을 경우 RuntimeException을 던짐
		        throw new RuntimeException("게시글이 존재하지 않습니다. id: " + id);
		    }
		}

	  
	  // 게시판 글 수정 메서드
	  @Transactional
	  public void boardedit(Integer id, BoardDto updatedBoardDto) {
	      Board board = boardDao.findById(id)
	              .orElseThrow();

	      // 업데이트된 내용을 새로운 BoardDto 엔티티로 변환
	      Board updatedBoardEntity = updatedBoardDto.toEntity();

	      // 엔티티의 필드를 업데이트 (제목, 내용, 카테고리만 업데이트하도록 합니다.)
	      if (updatedBoardEntity.getTitle() != null) {
	          board.setTitle(updatedBoardEntity.getTitle());
	      }
	      if (updatedBoardEntity.getContent() != null) {
	          board.setContent(updatedBoardEntity.getContent());
	      }
	      if (updatedBoardEntity.getCategory() != null) {
	          board.setCategory(updatedBoardEntity.getCategory());
	      }

	      // 수정된 게시글을 저장합니다.
	      boardDao.save(board);
	  }
	  
	  //게시판 글 검색 메서드
	  @Transactional
	  public List<Board> searchBoard(String keyword, String option) {
	      return boardDao.searchBoard(keyword, option);
	  }
	  
	  //좋아요 메서드
	  @Transactional
	  public BoardDto like(Integer id, Integer membersId) {
		    Optional<Board> boardOptional = boardDao.findById(id);
		    if (boardOptional.isPresent()) {
		        Board board = boardOptional.get();
		        
		        //좋아요 증가 메서드
		        Integer likecount = board.getLikecount() + 1;
		        board.setLikecount(likecount);
		        boardDao.save(board);
		        
		        // 좋아요 정보 저장
	            BoardLike boardLike = new BoardLike();
	            boardLike.setMembersId(membersId);
	            boardLike.setBoardId(id);
	            
	            // EntityManager를 사용하여 저장
	            entityManager.persist(boardLike);

		        // 좋아요 증가 후의 정보를 포함하는 DTO를 생성하여 반환
		        BoardDto boardDto = new BoardDto();
		        boardDto.setId(id);
		        boardDto.setTitle(board.getTitle());
		        boardDto.setContent(board.getContent());
		        boardDto.setCreateDate(board.getCreateDate());
		        boardDto.setMembersId(board.getMembersId());
		        boardDto.setViewcount(board.getViewcount());
		        boardDto.setLikecount(likecount);  // 추가

		        return boardDto;
		    }

		    // 게시글이 존재하지 않을 경우 RuntimeException을 던짐
		    throw new RuntimeException("게시글이 존재하지 않습니다. id: " + id);
		}

}
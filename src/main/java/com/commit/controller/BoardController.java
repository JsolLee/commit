package com.commit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commit.entity.Board;
import com.commit.model.BoardDto;
import com.commit.service.BoardService;

@RestController
public class BoardController {
    @Autowired
	private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    
    @GetMapping("/community")
    public List<Board> getBoard(){
    	List<Board> result = boardService.getBoard();
    	return result;
    }
    
    
    //글 쓰기 요청
    @PostMapping("/Community/boardwrite")
    public ResponseEntity<Integer> boardWrite(@RequestBody BoardDto boardDto) {
        try {
            Integer createdBoardId = boardService.boardWrite(boardDto);
            return new ResponseEntity<>(createdBoardId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //글 목록 조회 요청
    @GetMapping("/Community/boardlist")
    public Page<BoardDto> list(@RequestParam(name = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Direction.ASC, "id"));
        return boardService.getBoardList(pageable);
    }
    
    //글 상세 페이지 요청
    @GetMapping("/Community/boarddetail/{id}")
    public BoardDto detail(@PathVariable("id") Integer id) {
        return boardService.getBoard(id);
    }
    
    //글 삭제 요청
    @DeleteMapping("/Community/boarddetail/{id}")
    public void delete(@PathVariable("id") Integer id) {
        boardService.boarddelete(id);
    }
    
    // 글 수정 페이지 가져오기
    @GetMapping("/Community/boardedit/{id}")
    public ResponseEntity<BoardDto> showboardedit(@PathVariable("id") Integer id) {
        BoardDto boardDto = boardService.getBoard(id);
        return ResponseEntity.ok(boardDto);
    }

    // 글 수정 요청 처리
    @PostMapping("/Community/boardedit/{id}")
    public ResponseEntity<Void> boardedit(@PathVariable("id") Integer id, @RequestBody BoardDto updatedBoardDto) {
        boardService.boardedit(id, updatedBoardDto);
        return ResponseEntity.ok().build();
    }
}
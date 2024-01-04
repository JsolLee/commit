package com.commit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commit.entity.Board;
import com.commit.model.BoardDto;
import com.commit.service.BoardService;

@RestController
public class BoardController {
	private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    
    @GetMapping("/community")
    public List<Board> getBoard(){
    	List<Board> result = boardService.getAllBoard();
    	return result;
    }
    
    
    //글 쓰기 요청
    @PostMapping("/boardwrite")
    public ResponseEntity<Integer> boardWrite(@RequestBody BoardDto boardDto) {
        try {
            Integer createdBoardId = boardService.boardWrite(boardDto);
            return new ResponseEntity<>(createdBoardId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //글 목록 조회 요청
    @GetMapping("/boardlist")
    public List<BoardDto> list() {
        return boardService.getBoardList();
    }
    
    //글 상세 페이지 요청
    @GetMapping("/boarddetail/{id}")
    public BoardDto detail(@PathVariable("id") Integer id) {
        return boardService.getBoard(id);
    }
    
    //글 삭제 요청
    @DeleteMapping("/boarddetail/{id}")
    public void delete(@PathVariable("id") Integer id) {
        boardService.boarddelete(id);
    }
    
    // 글 수정 페이지 가져오기
    @GetMapping("/boardedit/{id}")
    public ResponseEntity<BoardDto> showboardedit(@PathVariable("id") Integer id) {
        BoardDto boardDto = boardService.getBoard(id);
        return ResponseEntity.ok(boardDto);
    }

    // 글 수정 요청 처리
    @PostMapping("/boardedit/{id}")
    public ResponseEntity<Void> boardedit(@PathVariable("id") Integer id, @RequestBody BoardDto updatedBoardDto) {
        boardService.boardedit(id, updatedBoardDto);
        return ResponseEntity.ok().build();
    }
}
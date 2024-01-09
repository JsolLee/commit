package com.commit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commit.model.NewsCommentDto;
import com.commit.service.NewsCommentService;

@RestController
@RequestMapping("/news")
public class NewsCommentController {

    @Autowired
    private NewsCommentService newsCommentService;
    
    // 뉴스 ID에 따른 댓글 목록 조회
    @GetMapping("/{newsId}/comments")
    public ResponseEntity<List<NewsCommentDto>> getCommentsByNewsId(@PathVariable Integer newsId) {
        List<NewsCommentDto> comments = newsCommentService.getCommentsByNewsId(newsId);
        return ResponseEntity.ok(comments);
    }

    // 특정 댓글에 대한 대댓글 목록 조회
    @GetMapping("/comments/{parentId}/replies")
    public ResponseEntity<List<NewsCommentDto>> getRepliesByCommentId(@PathVariable Integer parentId) {
        List<NewsCommentDto> replies = newsCommentService.getRepliesByCommentId(parentId);
        return ResponseEntity.ok(replies);
    }

    // 댓글 추가
    @PostMapping("/comments")
    public ResponseEntity<NewsCommentDto> addComment(@RequestBody NewsCommentDto newsCommentDto) {
        NewsCommentDto savedComment = newsCommentService.addComment(newsCommentDto);
        return ResponseEntity.ok(savedComment);
    }
}

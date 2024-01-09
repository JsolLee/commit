package com.commit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commit.model.NewsCommentDto;
import com.commit.service.NewsCommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/news")
public class NewsCommentController {

    @Autowired
    private NewsCommentService newsCommentService;
    
    // 뉴스 ID에 따른 댓글 목록 조회
    @GetMapping("/{newsId}/comments")
    public ResponseEntity<List<NewsCommentDto>> getCommentsByNewsId(@PathVariable("newsId") Integer newsId) {
        List<NewsCommentDto> comments = newsCommentService.getCommentsByNewsId(newsId);
        return ResponseEntity.ok(comments);
    }

    // 특정 댓글에 대한 대댓글 목록 조회
    @GetMapping("/comments/{parentId}/replies")
    public ResponseEntity<List<NewsCommentDto>> getRepliesByCommentId(@PathVariable("newsId") Integer parentId) {
        List<NewsCommentDto> replies = newsCommentService.getRepliesByCommentId(parentId);
        return ResponseEntity.ok(replies);
    }

    // 댓글 추가
    @PostMapping("/certification/comments")
    public ResponseEntity<?> addComment(@RequestBody NewsCommentDto commentDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("members_Id") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 세션에서 members_Id를 가져와서 사용
        Integer membersId = (Integer) session.getAttribute("members_Id");
        commentDto.setMembersId(membersId); // DTO에 membersId 설정
        NewsCommentDto addedComment = newsCommentService.addComment(commentDto);

        return ResponseEntity.ok(addedComment);
    }
}

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
    public ResponseEntity<List<NewsCommentDto>> getRepliesByCommentId(@PathVariable("parentId") Integer parentId) {
        List<NewsCommentDto> replies = newsCommentService.getRepliesByCommentId(parentId);
        return ResponseEntity.ok(replies);
    }

    // 댓글 추가 : 로그인한 사용자만 가능
    @PostMapping("/certification/comments")
    public ResponseEntity<?> addComment(@RequestBody NewsCommentDto commentDto) {
        String memberIdStr = commentDto.getMemberId(); // DTO에서 memberId를 가져옵니다.
        if (memberIdStr == null || memberIdStr.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            int memberId = Integer.parseInt(memberIdStr); // 문자열을 정수로 변환
            commentDto.setMembersId(memberId); // 변환된 정수를 setMembersId에 전달
            NewsCommentDto addedComment = newsCommentService.addComment(commentDto);

            return ResponseEntity.ok(addedComment);
        } catch (NumberFormatException e) {
            // memberId가 숫자 형태가 아닌 경우 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid member ID format.");
        }
    }

}

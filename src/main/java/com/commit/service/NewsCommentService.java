package com.commit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commit.entity.NewsComment;
import com.commit.model.NewsCommentDto;
import com.commit.repository.NewsCommentDao;

@Service
public class NewsCommentService {

    @Autowired
    private NewsCommentDao newsCommentDao;
    
    // 뉴스 ID에 따른 댓글 목록 조회
    public List<NewsCommentDto> getCommentsByNewsId(Integer newsId) {
        return newsCommentDao.findByNewsId(newsId).stream()
                .map(NewsCommentDto::convertToDto)
                .collect(Collectors.toList());
    }

    // 특정 댓글에 대한 대댓글 목록 조회
    public List<NewsCommentDto> getRepliesByCommentId(Integer parentId) {
        return newsCommentDao.findByParentId(parentId).stream()
                .map(NewsCommentDto::convertToDto)
                .collect(Collectors.toList());
    }

    // 댓글 추가
    public NewsCommentDto addComment(NewsCommentDto newsCommentDto) {
        NewsComment newsComment = convertToEntity(newsCommentDto);
        newsComment.setDeleteYN("N"); // deleteYN에 기본값 'N' 설정
        newsComment.setParentId(0); // parentId에 기본값 '0' 설정
        newsComment = newsCommentDao.save(newsComment);
        return NewsCommentDto.convertToDto(newsComment);
    }
    
    // DTO를 엔티티로 변환하는 메소드
    private NewsComment convertToEntity(NewsCommentDto dto) {
        NewsComment newsComment = new NewsComment();
        newsComment.setMembersId(dto.getMembersId());
        newsComment.setNewsId(dto.getNewsId());
        newsComment.setParentId(dto.getParentId());
        newsComment.setContent(dto.getContent());
        return newsComment;
    }
}

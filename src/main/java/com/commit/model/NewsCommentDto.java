// NewsCommentDto.java
package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.NewsComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsCommentDto {
    private Integer id;
    private Integer membersId;
    private String memberId;
    private Integer newsId; 
    private Integer parentId;
    private String content;
    private Timestamp createDate;
    private Timestamp updateDate;
    private Timestamp deleteDate;
    private String deleteYN;
    private String nickName; // 추가된 필드
    private String newsTitle; // 추가된 필드

    public static NewsCommentDto convertToDto(NewsComment newsComment) {
        
    	NewsCommentDto dto = new NewsCommentDto();
    	
        // 여기서 member가 null인지 확인
        if (newsComment.getMember() != null) {
            dto.setMemberId(newsComment.getMember().getId().toString());
        } else {
            dto.setMemberId("아이디가 없습니다!"); // null 일 경우에 대체
        }
    	
    	return NewsCommentDto.builder()
                .id(newsComment.getId())
                .membersId(newsComment.getMember().getId())
                .memberId(newsComment.getMember().getMemberId())
                .newsId(newsComment.getNews().getId())
                .parentId(newsComment.getParentId())
                .content(newsComment.getContent())
                .createDate(newsComment.getCreateDate())
                .updateDate(newsComment.getUpdateDate())
                .deleteDate(newsComment.getDeleteDate())
                .nickName(newsComment.getMember().getNickName()) // 회원 닉네임 추가
                .newsTitle(newsComment.getNews().getTitle()) // 뉴스 제목 추가
                .build();
    }
}

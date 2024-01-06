package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.News;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
	private Integer id;
	private String category;
	private String title;
	private String subtitle;
	private String content; 
	private String origin; 
	private String image;
	private String writer;
	private Integer viewcount; 
	private Integer likecount; 
	private Timestamp originDate; 
	private Timestamp createDate; 
	private String deleteYN;
	
	// json type으로 컨버팅 : 메서드 이름도 명확하게 하기
    public static NewsDto convertToDto(News news) {
    	return NewsDto.builder()
                .id(news.getId())
                .category(news.getCategory())
                .title(news.getTitle())
                .subtitle(news.getSubtitle())
                .content(news.getContent())
                .origin(news.getOrigin())
                .image(news.getImage())
                .writer(news.getWriter())
                .viewcount(news.getViewcount())
                .likecount(news.getLikecount())
                .originDate(news.getOriginDate())
                .createDate(news.getCreateDate())
                .build();
    }
}
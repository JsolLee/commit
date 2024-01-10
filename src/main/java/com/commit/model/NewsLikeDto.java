package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.NewsLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsLikeDto {
	private Integer id;
	private Integer membersId;
	private Integer newsId;
	private Timestamp createDate;
	
	// json type으로 컨버팅 : 메서드 이름도 명확하게 하기
	public static NewsLikeDto convertToDto(NewsLike newsLike) {
		return NewsLikeDto.builder()
				.id(newsLike.getId())
				.membersId(newsLike.getMembersId())
				.newsId(newsLike.getNewsId())
				.createDate(newsLike.getCreateDate())
				.build();
	}
}

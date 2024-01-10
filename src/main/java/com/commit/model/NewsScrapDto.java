package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.NewsScrap;

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
public class NewsScrapDto {
	private Integer id;
	private Integer membersId;
	private Integer newsId; 
	private Timestamp createDate;

	public static NewsScrapDto convertToDto(NewsScrap newsScrap) {
		return NewsScrapDto.builder()
				.id(newsScrap.getId())
				.membersId(newsScrap.getMembers().getId())
				.newsId(newsScrap.getNews().getId())
				.createDate(newsScrap.getCreateDate())
				.build();
	}
}


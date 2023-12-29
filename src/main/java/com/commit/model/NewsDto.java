package com.commit.model;

import java.sql.Timestamp;

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
}
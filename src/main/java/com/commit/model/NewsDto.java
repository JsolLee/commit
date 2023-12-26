package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class NewsDto {
	private Integer id;
	private String category;
	private String title;
	private String subtitle;
	private String content; 
	private String orgin; 
	private String image;
	private String writer;
	private Integer viewcount; 
	private Integer likecount; 
	private Timestamp originDate; 
	private Timestamp createDate; 
	private String deleteYN; 
}

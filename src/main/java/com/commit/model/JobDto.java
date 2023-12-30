package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobDto {
	private Integer id;
	private String title;
	private String content;
	private String career;
	private String degree; 
	private String location; 
	private String image; 
	private Integer viewcount;
	private Timestamp createDate; 
	private Timestamp finishDate;
}

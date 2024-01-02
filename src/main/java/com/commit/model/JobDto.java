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
public class JobDto {
	private Integer id;
	private String title;
	private String career;
	private String degree; 
	private String companyname; 
	private String location; 
	private String image; 
	private Integer viewcount;
	private Timestamp createDate; 
	private Timestamp finishDate;
}

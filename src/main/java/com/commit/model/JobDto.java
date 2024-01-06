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
	private String location2; // 지역 세부
	private String type; // 고용 형태
	private String page; // 홈페이지 주소
	private String salary; // 급여
	private String size; // 회사 규모
	private String content; // 내용
}

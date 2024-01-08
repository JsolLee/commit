package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "job")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "JOB_ID")
	private Integer id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String career; // 경력
	@Column(nullable = false)
	private String degree; // 학력
	@Column(nullable = false)
	private String companyname; //회사명
	@Column(nullable = false)
	private String location; // 근무지
	@Column(nullable = false)
	private String location2; // 지역 세부
	@Column(nullable = true)
	private String image; // 채용 상세보기
	@Column(nullable = false)
	private Integer viewcount; // 조회수
	@Column(nullable = false)
	private String type; // 고용 형태
	@Column(nullable = true)
	private String page; // 홈페이지 주소
	@Column(nullable = false)
	private String salary; // 급여
	@Column(nullable = false)
	private String size; // 회사 규모
	@Column(nullable = true)
	private String content; // 내용
	@CreatedDate
	@Column(nullable = false)
	private Timestamp createDate; // 채용 게시일(등록일) 마김-7
	@Column(nullable = true)
	private Timestamp finishDate_D; // 채용 공고 마감일(날짜 형식)
	@Column(nullable = true)
	private String finishDate_S; // 채용 공고 마감일(문자열 형식 : 채용시, 상시)
}

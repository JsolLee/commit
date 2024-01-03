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
	private String title;
	private String career; // 경력
	private String degree; // 학력
	private String companyname; //회사명
	private String location; // 근무지
	private String image; // 채용 상세보기
	private String finishDate_S; // 채용 공고 마감일(문자열 형식 : 채용시, 상시)	
	private Integer viewcount; // 조회수
	@CreatedDate
	private Timestamp createDate; // 채용 게시일(등록일) 마김-7
	private Timestamp finishDate_D; // 채용 공고 마감일(날짜 형식)
}

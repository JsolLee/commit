package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="job")
public class Job {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "JOB_ID")
	private Integer id;
	private String title;
	private String content;
	private String career; //경력
	private String degree; //학력
	private String location; // 근무지
	private String image; // 채용 상세보기
	private Integer viewcount; //조회수
	@CreatedDate
	private Timestamp createDate; //채용 게시일(등록일) 마김-7
	private Timestamp finishDate; // 채용 공고 마감일
}

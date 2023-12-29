package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "job")
public class Job {
	@Builder
	public Job(Integer id, String title, String content, String career, String degree, String companyname,
			String location, String image, Integer viewcount, Timestamp createDate, Timestamp finishDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.career = career;
		this.degree = degree;
		this.companyname = companyname;
		this.location = location;
		this.image = image;
		this.viewcount = viewcount;
		this.createDate = createDate;
		this.finishDate = finishDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "JOB_ID")
	private Integer id;
	private String title;
	private String content;
	private String career; // 경력
	private String degree; // 학력
	private String companyname; //회사명
	private String location; // 근무지
	private String image; // 채용 상세보기
	private Integer viewcount; // 조회수
	@CreatedDate
	private Timestamp createDate; // 채용 게시일(등록일) 마김-7
	private Timestamp finishDate; // 채용 공고 마감일
}

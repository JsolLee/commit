package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "NEWS_ID")
	private Integer id;
	private String category;
	private String title;
	private String subtitle;
	private String content; // 뉴스 기사 데이터
	private String origin; // 출처
	@Column(nullable = true)
	private String image;
	private String writer; //뉴스 작성자
	private Integer viewcount; //조회수
	private Integer likecount; // 좋아요한수
	private Timestamp originDate; //뉴스 원본 작성일
	@CreatedDate
	private Timestamp createDate; // 뉴스 게시일
	private String deleteYN; //삭제여부
}

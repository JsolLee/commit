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
@Table(name="news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEWS_ID")
	private Integer id;
	@Column(nullable=false)
	private String category;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String subtitle;
	@Column(nullable=false)
	private String content; // 뉴스 기사 데이터
	@Column(nullable=false)
	private String origin; // 출처
	@Column(nullable = true)
	private String image;
	@Column(nullable=false)
	private String writer; //뉴스 작성자
	@Column(nullable=false)
	private Integer viewcount; //조회수
	@Column(nullable=false)
	private Integer likecount; // 좋아요한수
	@Column(nullable=false)
	private Timestamp originDate; //뉴스 원본 작성일
	@CreatedDate
	@Column(nullable=false)
	private Timestamp createDate; // 뉴스 게시일
	@Column(nullable=false)
	private String deleteYN; //삭제여부
}

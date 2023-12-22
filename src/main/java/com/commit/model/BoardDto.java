package com.commit.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BoardDto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOARD_ID")
	private Integer id;
	private String category;
	private String title;
	private String content;
	@CreationTimestamp
	private Timestamp createDate; // 뉴스 게시일
	private String image;
	private int count; //조회수
	private int likecount; // 좋아요한수
	private boolean likeboolean; //좋아요
	private int scrapcount; // 스크랩한수
	private boolean scrapboolean; // 스크랩 여부(마이페이사용)
	private int report; //신고수
	@Column(name = "MEMBER_ID")
	private Integer memberId; //fk연결
}

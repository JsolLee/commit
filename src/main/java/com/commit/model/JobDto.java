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
public class JobDto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "JOB_ID")
	private Integer id;
	private String title;
	private String content;
	private String career; //경력
	private String degree; //학력
	private String location; // 근무지
	@CreationTimestamp
	private Timestamp createDate; //채용 게시일(등록일) 마김-7
	private Timestamp finishDate; // 채용 공고 마감일
	private String image; // 채용 상세보기
	private int count; //조회수
	private boolean likeboolean; // 관심기업 (마이페이지사용)
	private int scrapcount; // 스크랩한수
	private boolean scrapboolean; // 스크랩 여부(마이페이사용)
	private int report; //신고수
	@Column(name = "MEMBER_ID")
	private Integer memberId; //fk연결
}

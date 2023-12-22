package com.commit.model;

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
public class MypageDto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "MYPAGE_ID")
	private Integer id;
	@Column(name = "MEMBER_ID")
	private Integer memberId; //fk연결
	@Column(name = "NEWS_ID")
	private Integer newsId; //fk연결
	@Column(name = "BOARD_ID")
	private Integer boardId; //fk연결
	@Column(name = "JOB_ID")
	private Integer jobId; //fk연결
	@Column(name = "COMMENT_ID")
	private Integer commentId; //fk연결
}

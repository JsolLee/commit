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
public class CommentDto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COMMENT_ID")
	private Integer id;
	private String content;
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp updateDate;
	private int likecount; // 좋아요한수
	private boolean likeboolean; 
	private int report; //신고수
	@Column(name = "MEMBER_ID")
	private Integer memberId; //fk연결
	@Column(name = "NEWS_ID")
	private Integer newsId; //fk연결
	@Column(name = "BOARD_ID")
	private Integer boardId; //fk연결
}

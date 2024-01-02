package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
@Table(name="board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOARD_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID")
	private Integer membersId; //fk연결
	@Column(name = "BOARDFILE_ID", nullable=true)
	private Integer boardFIleId; //fk연결
	private String category;
	private String title;
	private String content;
	private Integer viewcount;
	private Integer likecount;
	@CreatedDate
	private Timestamp createDate;
	@LastModifiedDate
	@Column(nullable = true)
	private Timestamp updateDate;
	private String deleteYN; //삭제 여부
}
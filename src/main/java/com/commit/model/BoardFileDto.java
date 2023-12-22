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
public class BoardFileDto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOARDFILE_ID")
	private Integer id;
	private String originName;
	private String dir;
	private String name;
	private String type;
	private Integer size;
	@CreationTimestamp
	private Timestamp createDate; // 뉴스 게시일
	private Timestamp updateDate;

	private int report; // 신고수
	@Column(name = "BOARD_ID")
	private Integer boardId; // fk연결
}

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
@Table(name="boardreport")
public class BoardReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOARDREPORT_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID")
	private Integer membersId; //신고한 사람 아이디
	@Column(name = "BOARD_ID")
	private Integer boardId;
	private String content;
	@CreatedDate
	private Timestamp createDate;
	@Column(nullable = true)
	private Timestamp deleteDate;
	private String deleteYN; //삭제 여부
	
}
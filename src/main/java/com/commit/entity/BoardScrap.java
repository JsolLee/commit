package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "members", "board" })
@Table(name = "boardscrap")
public class BoardScrap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARDSCRAP_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable = false)
	private Integer membersId;
	@Column(name = "BOARD_ID", nullable = false)
	private Integer boardId;
	@CreatedDate
	@Column(nullable = false)
	private Timestamp createDate;

	// Members 테이블과의 관계 설정
	@ManyToOne
	@JoinColumn(name = "MEMBERS_ID", insertable = false, updatable = false)
	private Members members;

	// Job 테이블과의 관계 설정
	@ManyToOne
	@JoinColumn(name = "BOARD_ID", insertable = false, updatable = false)
	private Board board;

}

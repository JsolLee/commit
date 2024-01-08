package com.commit.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name="board")
@AllArgsConstructor
@Builder
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId; //fk연결
	@Column(name = "BOARDFILE_ID", nullable=true)
	private Integer boardFIleId; //fk연결
	@Column(nullable=false)
	private String category;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String content;
	@Column(nullable=false)
	private Integer viewcount;
	@Column(nullable=false)
	private Integer likecount;
	@CreatedDate
	@Column(nullable=false)
	private Timestamp createDate;
	@LastModifiedDate
	@Column(nullable = true, insertable = false)
	private Timestamp updateDate;
	@Column(nullable=false)
	private String deleteYN; //삭제 여부
}

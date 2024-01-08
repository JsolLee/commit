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
@Table(name="newsreport")
public class NewsReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "NEWSREPORT_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId; //신고한 사람 아이디
	@Column(name = "NEWS_ID", nullable=false)
	private Integer newsId;
	@Column(nullable=false)
	private String content;
	@CreatedDate
	@Column(nullable=false)
	private Timestamp createDate;
	@Column(nullable = true)
	private Timestamp deleteDate;
	@Column(nullable=false)
	private String deleteYN; //삭제 여부
	
}

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
@Table(name="newscomment")
public class NewsComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "NEWSCOMMENT_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID")
	private Integer membersId; 
	@Column(name = "NEWS_ID")
	private Integer newsId;
	private Integer parentId; //default=0 //selfjoin
	private String content;
	@CreatedDate
	private Timestamp createDate;
	@LastModifiedDate
	@Column(nullable=true)
	private Timestamp updateDate;
	@Column(nullable=true)
	private Timestamp deleteDate;
	private String deleteYN;
}

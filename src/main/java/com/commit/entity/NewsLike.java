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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "members", "news" })
@Table(name = "newslike")
public class NewsLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEWSLIKE_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable = false)
	private Integer membersId;
	@Column(name = "NEWS_ID", nullable = false)
	private Integer newsId;
	@CreatedDate
	@Column(nullable = false)
	private Timestamp createDate;

	// Members 테이블과의 관계 설정
	@ManyToOne
	@JoinColumn(name = "MEMBERS_ID", insertable = false, updatable = false)
	private Members members;

	// News 테이블과의 관계 설정
	@ManyToOne
	@JoinColumn(name = "NEWS_ID", insertable = false, updatable = false)
	private News news;
}

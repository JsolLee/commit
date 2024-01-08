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
@ToString(exclude = {"member", "news"})
@EntityListeners(AuditingEntityListener.class)
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
	@Column(nullable=true, insertable = false)
	private Timestamp updateDate;
	@Column(nullable=true)
	private Timestamp deleteDate;
	private String deleteYN;
	
    // Members 테이블과의 관계 설정
    @ManyToOne
    @JoinColumn(name = "MEMBERS_ID", insertable = false, updatable = false)
    private Members member;

    // News 테이블과의 관계 설정
    @ManyToOne
    @JoinColumn(name = "NEWS_ID", insertable = false, updatable = false)
    private News news;
}

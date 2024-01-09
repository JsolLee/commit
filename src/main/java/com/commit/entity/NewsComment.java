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
	@Column(name = "NEWSCOMMENT_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId; 
	@Column(name = "NEWS_ID", nullable=false)
	private Integer newsId;
	@Column(nullable=false)
	private Integer parentId; //default=0 //selfjoin
	@Column(nullable=false)
	private String content;
	@CreatedDate
	@Column(nullable=false)
	private Timestamp createDate;
	@LastModifiedDate
	@Column(nullable=true, insertable = false)
	private Timestamp updateDate;
	@Column(nullable=true)
	private Timestamp deleteDate;
	@Column(nullable=false)
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

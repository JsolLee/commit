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
@Table(name="commentlike")
public class CommentLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COMMENTLIKE_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId;
	@Column(name = "NEWSCOMMENT_ID", nullable=true)
	private Integer newsCommentId;
	@Column(name = "BOARDCOMMENT_ID", nullable=true)
	private Integer boardCommentId;
	@CreatedDate
	@Column(nullable=false)
	private Timestamp createDate;

}

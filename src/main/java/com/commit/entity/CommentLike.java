package com.commit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="commentlike")
public class CommentLike {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COMMENTLIKE_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID")
	private Integer membersId;
	@Column(name = "NEWSCOMMENT_ID", nullable=true)
	private Integer newscommentId;
	@Column(name = "BOARDCOMMENT_ID", nullable=true)
	private Integer boardcommentId;
	
}

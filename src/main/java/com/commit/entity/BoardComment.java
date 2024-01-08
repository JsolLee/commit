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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name="boardcomment")
public class BoardComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARDCOMMENT_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId; 
	@Column(name = "BOARD_ID", nullable=false)
	private Integer boardId;
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
}

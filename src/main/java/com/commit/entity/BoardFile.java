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
@Table(name="boardfile")
public class BoardFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOARDFILE_ID")
	private Integer id;
	@Column(name = "BOARD_ID", nullable=false)
	private Integer boardId; // fk연결
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId; // fk연결
	@Column(nullable=false)
	private String originName;
	@Column(nullable=false)
	private String dir;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String type;
	@Column(nullable=false)
	private Integer size;
	@CreatedDate
	@Column(nullable=false)
	private Timestamp createDate;
	@LastModifiedDate
	@Column(nullable=true, insertable = false)
	private Timestamp updateDate;
}

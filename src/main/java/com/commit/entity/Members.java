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
@Table(name="members")
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "MEMBERS_ID") // 컬럼명을 새로 만들어 준다.
	private Integer id;
	private String role;
	private String memberId;
	private String memberPw;
	private String email;
	private String nickName;
	@CreatedDate
	private Timestamp createDate;
	@LastModifiedDate
	@Column(nullable = true)
	private Timestamp updateDate;
	private String memberOut;
}

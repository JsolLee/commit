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
@Table(name="jobscrap")
public class JobScrap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "JOBSCRAP_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable=false)
	private Integer membersId;
	@Column(name = "JOB_ID", nullable=false)
	private Integer jobId;
	@CreatedDate
	@Column(nullable = false)
	private Timestamp createDate;
}

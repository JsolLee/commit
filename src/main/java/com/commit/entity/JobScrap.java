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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"members", "job"})
@Table(name = "jobscrap")
public class JobScrap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "JOBSCRAP_ID")
	private Integer id;
	@Column(name = "MEMBERS_ID", nullable = false)
	private Integer membersId;
	@Column(name = "JOB_ID", nullable = false)
	private Integer jobId;
	@CreatedDate
	@Column(nullable = false)
	private Timestamp createDate;

    // Members 테이블과의 관계 설정
	@ManyToOne
	@JoinColumn(name = "MEMBERS_ID", insertable = false, updatable = false)
	private Members members;
	
	// Job 테이블과의 관계 설정
    @ManyToOne
    @JoinColumn(name = "JOB_ID", insertable = false, updatable = false)
    private Job job;
}

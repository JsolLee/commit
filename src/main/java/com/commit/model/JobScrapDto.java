package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobScrapDto {
	private Integer id;
	private Integer membersId;
	private Integer jobId; 	
	private Timestamp createDate;
}

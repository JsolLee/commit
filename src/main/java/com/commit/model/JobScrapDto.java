package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.JobScrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobScrapDto {
	private Integer id;
	private Integer membersId;
	private Integer jobId; 	
	private Timestamp createDate;
	
	public static JobScrapDto converToDto(JobScrap jobScrap) {
		return JobScrapDto.builder()
				.id(jobScrap.getId())
				.membersId(jobScrap.getMembers().getId())
				.jobId(jobScrap.getJob().getId())
				.createDate(jobScrap.getCreateDate())
				.build();
	}
}

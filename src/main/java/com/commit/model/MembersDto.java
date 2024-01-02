package com.commit.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembersDto {
	private Integer id;
	private String role;
	private String memberId;
	private String memberPw;
	private String email;
	private String nickName;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String memberOut;
	
}

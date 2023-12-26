package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

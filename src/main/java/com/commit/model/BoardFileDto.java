package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardFileDto {
	private Integer id;
	private Integer boardId;
	private Integer membersId;
	private String originName;
	private String dir;
	private String name;
	private String type;
	private Integer size;
	private Timestamp createDate;
	private Timestamp updateDate;
	
}

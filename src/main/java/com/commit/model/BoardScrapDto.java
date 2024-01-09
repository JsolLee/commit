package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardScrapDto {
	private Integer id;
	private Integer membersId;
	private Integer boardId; 
	private Timestamp createDate;
}

package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
	private Integer id;
	private Integer membersId;
	private Integer boardFIleId; 
	private String category;
	private String title;
	private String content;
	private Integer viewcount;
	private Integer likecount;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String deleteYN; 

}

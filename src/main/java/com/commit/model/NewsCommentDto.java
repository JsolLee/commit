package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsCommentDto {
	private Integer id;
	private Integer membersId;
	private Integer newsId; 
	private Integer parentId;
	private String content;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Timestamp deleteDate;
	private String deleteYN;
	
}

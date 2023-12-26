package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommentDto {
	private Integer id;
	private Integer membersId;
	private Integer boardId;
	private Integer parentId;
	private String content;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Timestamp deleteDate;
	private String deleteYN;
}

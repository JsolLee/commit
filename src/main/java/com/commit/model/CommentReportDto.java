package com.commit.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentReportDto {
	private Integer id;
	private Integer membersId; //신고한 사람
	private Integer newsCommentId; 
	private Integer boardCommentId; 
	private String content;
	private Timestamp createDate;
	private Timestamp deleteDate;
	private String deleteYN;
}

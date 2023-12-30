package com.commit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentLikeDto {
	private Integer id;
	private Integer membersId;
	private Integer newscommentId;
	private Integer boardcommentId;
	
}

package com.commit.model;

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
}

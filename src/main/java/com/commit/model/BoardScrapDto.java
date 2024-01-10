package com.commit.model;

import java.sql.Timestamp;


import com.commit.entity.BoardScrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardScrapDto {
	private Integer id;
	private Integer membersId;
	private Integer boardId; 	
	private Timestamp createDate;
	
	public static BoardScrapDto convertToDto(BoardScrap boardScrap) {
		return BoardScrapDto.builder()
				.id(boardScrap.getId())
				.membersId(boardScrap.getMembers().getId())
				.boardId(boardScrap.getBoard().getId())
				.createDate(boardScrap.getCreateDate())
				.build();
	}

}

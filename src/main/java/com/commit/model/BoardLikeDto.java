package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.BoardLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardLikeDto {
	private Integer id;
	private Integer membersId;
	private Integer boardId;
	private Timestamp createDate;
	
	public static BoardLikeDto convertToDto(BoardLike boardLike) {
		return BoardLikeDto.builder()
				.id(boardLike.getId())
				.membersId(boardLike.getMembersId())
				.boardId(boardLike.getBoardId())
				.createDate(boardLike.getCreateDate())
				.build();
	}
}

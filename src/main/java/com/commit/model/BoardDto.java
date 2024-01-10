package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class BoardDto {
	private Integer id;
	private Integer membersId=3;
	private Integer boardFIleId; 
	private String category;
	private String title;
	private String content;
	private Integer viewcount;
	private Integer likecount;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String deleteYN = "N"; 
	
	private String nickname;
	
	@Builder
	public BoardDto(Integer id, Integer membersId, Integer boardFIleId, String category, 
	                String title, String content, Integer viewcount, Integer likecount, 
	                Timestamp createDate, Timestamp updateDate, String deleteYN, String nickname) {
	    this.id = id;
	    this.membersId = membersId;
	    this.boardFIleId = boardFIleId;
	    this.category = category;
	    this.title = title;
	    this.content = content;
	    this.viewcount = viewcount;
	    this.likecount = likecount;
	    this.createDate = createDate;
	    this.updateDate = updateDate;
	    this.deleteYN = deleteYN;
	    this.nickname = nickname;
	}
	
	public Board toEntity() {
        return Board.builder()
        		.id(this.id)
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .membersId(this.membersId)
                .viewcount(0)
                .likecount(0)
                .createDate(this.createDate)
                .deleteYN(this.deleteYN)                
                .build();
    }
}

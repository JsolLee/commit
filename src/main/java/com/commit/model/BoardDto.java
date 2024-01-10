/*
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
	private Integer membersId = 2;
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
*/

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
    private Integer membersId;
//    private Integer membersId = 4;//세션에서 못 받아와서
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
/*
    public Board toEntity() {
        return Board.builder()
                .id(this.id)
                .membersId(this.membersId)
                .category(this.category)
                .title(this.title)
                .content(this.content)
                .viewcount(0)
                .likecount(0)
                .createDate(this.createDate)
                .deleteYN(this.deleteYN)
                .build();
    }
    
       private Integer id;
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
    */
    
    public static BoardDto convertToDto(Board board) {
    	return BoardDto.builder()
    			.id(board.getId())
    			.membersId(board.getMembersId())
    			.category(board.getCategory())
    			.viewcount(board.getViewcount())
    			.likecount(board.getLikecount())
    			.createDate(board.getCreateDate())
    			.updateDate(board.getUpdateDate())
    			.build();
    			
    }
} 
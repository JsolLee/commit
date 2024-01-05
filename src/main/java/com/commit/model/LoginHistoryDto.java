package com.commit.model;

import java.sql.Timestamp;

import com.commit.entity.LoginHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryDto {
	private Integer id;
	private String memberId;
	private Timestamp createDate;
	
	public static LoginHistoryDto fromEntity(LoginHistory loginHistory) {
		return LoginHistoryDto.builder()
				.id(loginHistory.getId())
				.memberId(loginHistory.getMemberId())
				.createDate(loginHistory.getCreateDate())
				.build();
	}
}
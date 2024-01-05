package com.commit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.commit.entity.LoginHistory;
import com.commit.model.LoginHistoryDto;
import com.commit.repository.HistoryDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryService {
	private final HistoryDao historyDao;
	
	public void saveLogOnLogin(LoginHistory loginHistory) {
		historyDao.save(loginHistory);
	}
	
	public List<LoginHistoryDto> getLoginHistoryDto(String memberId){
		List <LoginHistory> loginHistories = historyDao.findLoginHistoriesByMemberIdOrderByCreateDateDesc(memberId);
		
		return loginHistories.stream()
				.map(LoginHistoryDto::fromEntity)
				.collect(Collectors.toList());
	}
}

package com.commit.service;

import java.util.Date;
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
	
	/* ver1
	public void saveLogOnLogin(LoginHistory loginHistory) {
		historyDao.save(loginHistory);
	}
	*/
	
	// ver2
	public void saveLogOnLogin(String activitytype, String memberId) {
		LoginHistory loginHistory = new LoginHistory();
		loginHistory.setMemberId(memberId);
		if("logout".equals(activitytype)) loginHistory.setExpireDate(new Date());
		historyDao.save(loginHistory);
	}
	
	public List<LoginHistoryDto> getLoginHistoryDto(String memberId){
		List <LoginHistory> loginHistories = historyDao.findLoginHistoriesByMemberIdOrderByCreateDateDesc(memberId);
		
		return loginHistories.stream()
				.map(LoginHistoryDto::fromEntity)
				.collect(Collectors.toList());
	}
}

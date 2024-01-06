package com.commit.auth;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.commit.entity.LoginHistory;
import com.commit.service.HistoryService;
import com.commit.service.MembersService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MemberAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private final HistoryService historyService;
		
	public MemberAuthenticationSuccessHandler(HistoryService historyService) {
        this.historyService = historyService;
    }

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// SpringSecurity 인증 후 로그인 객체를 가져오기 위해 작성
		Object principal = authentication.getPrincipal();
		MembersDetails membersDetails = (MembersDetails) principal;
		
		String memberId = membersDetails.getUsername();
		
		
		LoginHistory loginHistory = LoginHistory.builder()
				.memberId(memberId)
				.build();
		
		//히스토리 저장작업
		//historyService.saveLogOnLogin(loginHistory);
		//회원 별 최종 로그인 날짜 업데이트 작업
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	

}

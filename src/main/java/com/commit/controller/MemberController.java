package com.commit.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.commit.entity.LoginHistory;
import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.service.HistoryService;
import com.commit.service.MembersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private HistoryService historyService;

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody MembersDto membersDto) {
		membersService.join(membersDto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/Login")
	public ResponseEntity<?> login(@RequestBody MembersDto membersDto, HttpSession session,
			HttpServletRequest httpServletRequest, LoginHistory loginHistory) {

		Optional<Members> members = membersService.findByMemberIdAndMemberPw(membersDto);
		if (members == null) {
//			System.out.println(members);
			session.setAttribute("msg", "Fail");
			return ResponseEntity.badRequest().body(members);
		} else {
			httpServletRequest.getSession().invalidate(); // 기존 세션 파기
			session = httpServletRequest.getSession(true); // 세션이 없으면 생성
			// 세션에 memberId를 넣어줌
			session.setAttribute("memberId", members.get().getMemberId());
			session.setMaxInactiveInterval(60 * 30); // 세션 유지 시간 30분으로 설정
			System.out.println("session : " + session.getAttribute("memberId"));
//			System.out.println(members);

			sessionList.put(session.getId(), session);
//			historyService.saveLogOnLogin(loginHistory);
		}
		return ResponseEntity.ok(members);
	}
	
	@GetMapping("/Logout")
	public ResponseEntity<?> logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession(false);
		System.out.println("1 :" + session.getId());
		System.out.println("sessionList1 :" + sessionList);
		if(session != null) {
			sessionList.remove(session.getId());
			session.invalidate();
		}
		System.out.println("2 :" + session.getId());
		System.out.println("sessionList2 :" + sessionList);
		
		return ResponseEntity.ok().build();
	}

	// 세션 리스트 확인용 코드
	public static Hashtable sessionList = new Hashtable();

	@GetMapping("/session-list")
	@ResponseBody
	public Map<String, String> sessionList() {
		Enumeration elements = sessionList.elements();
		Map<String, String> lists = new HashMap<>();
		while (elements.hasMoreElements()) {
			HttpSession session = (HttpSession) elements.nextElement();
			lists.put(session.getId(), String.valueOf(session.getAttribute("memberId")));
		}
		return lists;
	}

}

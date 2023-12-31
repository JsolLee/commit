package com.commit.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.commit.entity.LoginHistory;
import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.service.HistoryService;
import com.commit.service.MembersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			HttpServletRequest httpServletRequest) {

		Optional<Members> members = membersService.findByMemberIdAndMemberPw(membersDto);
		if (!members.isPresent()) {
			session.setAttribute("msg", "Fail");
			return ResponseEntity.badRequest().body("아이디 또는 비밀번호를 다시 입력하세요.");
		}else if(members.get().getMemberOut().equals("Y")) {
			session.setAttribute("msg", "Fail");
			return ResponseEntity.badRequest().body("이미 탈퇴한 회원입니다.");
		} else {
			httpServletRequest.getSession().invalidate(); // 기존 세션 파기
			session = httpServletRequest.getSession(true); // 세션이 없으면 생성
			// 세션에 memberId를 넣어줌
			session.setAttribute("memberId", members.get().getMemberId());
			session.setAttribute("members_Id", members.get().getId());
//			Integer membersId = (Integer) session.getAttribute("members_Id"); <-- 혹시 몰라 Integer로 캐스팅 해놓은 코드
			Object membersId = session.getAttribute("members_Id");
			session.setMaxInactiveInterval(60 * 30); // 세션 유지 시간 30분으로 설정
//			System.out.println("session : " + session.getAttribute("memberId"));
			// System.out.println(members);

			sessionList.put(session.getId(), session);
			
			historyService.saveLogOnLogin("login", membersDto.getMemberId());
		}
		return ResponseEntity.ok(members);
	}

	@GetMapping("/Logout")
	public ResponseEntity<?> logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession(false);
		
		if(session != null) {
			String memberId = session.getAttribute("memberId").toString();
			historyService.saveLogOnLogin("logout", memberId);

			sessionList.remove(session.getId());
			session.invalidate();
		}
		
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

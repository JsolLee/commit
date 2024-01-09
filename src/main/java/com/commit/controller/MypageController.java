package com.commit.controller;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.service.HistoryService;
import com.commit.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	@Autowired
	private MypageService mypageService;

	@Autowired
	private HistoryService historyService;

	@GetMapping("/mypage")
	public ResponseEntity<?> getMemberData(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		String memberId = session.getAttribute("memberId").toString();
		Optional<Members> members = mypageService.getMemberDetails(memberId);

		return ResponseEntity.ok(members);
	}

	@PostMapping("/userCheck")
	public ResponseEntity<?> login(@RequestBody MembersDto membersDto, HttpSession session,
			HttpServletRequest httpServletRequest) {
		Object memberIdObj = session.getAttribute("memberId");
		if (memberIdObj == null) {
			return ResponseEntity.badRequest().build();
		}

		String memberId = memberIdObj.toString();
		String memberPw = mypageService.getMemberPw(membersDto.getMemberId(), membersDto);

		if (memberPw == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	@Transactional
	@PutMapping("/nameEdit")
	public ResponseEntity<?> nameEdit(@RequestBody Map<String, String> body, HttpSession session,
			HttpServletRequest httpServletRequest) {
		String newName = body.get("newName");
		String nickName = mypageService.getNickName(body.get("nickName"));
		if (nickName == null) {
			return ResponseEntity.badRequest().build();
		} else {
			mypageService.setNewNickName(nickName, newName);
			return ResponseEntity.ok().build();
		}
	}

	@Transactional
	@PutMapping("/pwEdit")
	public ResponseEntity<?> pwEdit(@RequestBody Map<String, String> body, HttpSession session,
			HttpServletRequest httpServletRequest) {

		// 1. 프론트에서 받아온 멤버Id, 멤버Pw, 변경Pw를 변수에 저장
		String memberId = body.get("memberId");
		String memberPw = body.get("memberPw");
		String changePw = body.get("changePw");

		// 2. 프론트에서 받아온 멤버Pw와 db에 저장되어 있는 멤버Pw 일치 여부 확인(인코더)
		boolean checkPwMatch = mypageService.getMemberById(memberId, memberPw, changePw);

		// 3. 일치하다면 ResponseEntity에 "기존의 비밀번호와 일치합니다." 보내주기
		if (checkPwMatch) {
			return ResponseEntity.ok("기존의 비밀번호와 일치합니다.");
		}
		// 4. 그렇지 않다면 memberPw에 changePw로 업데이트
		else {
			mypageService.updateMemberPw(memberId, changePw);
			return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
		}
	}

	@Transactional
	@PutMapping("/memberExit")
	public ResponseEntity<?> memberExit(@RequestBody MembersDto membersDto, HttpSession session,
			HttpServletRequest httpServletRequest) {
		Object memberExitObj = membersDto.getMemberOut();
		Object memberIdObj = session.getAttribute("memberId");

		String memberExit = memberExitObj.toString();
		String memberId = memberIdObj.toString();
		if (memberExit.equals("N")) {
			return ResponseEntity.badRequest().build();
		} else {
			historyService.saveLogOnLogin("logout", memberId);

			mypageService.setMemberOut(memberId, memberExit);
			return ResponseEntity.ok().build();
		}
	}
}

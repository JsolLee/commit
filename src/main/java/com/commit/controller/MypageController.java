package com.commit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	@Autowired
	private MypageService mypageService;
	
	@GetMapping("/mypage")
	public ResponseEntity<?> getMemberData(HttpSession session, HttpServletRequest request){
		session = request.getSession();
		String memberId = session.getAttribute("memberId").toString();
		Optional<Members> members = mypageService.getMemberDetails(memberId);
		
		return ResponseEntity.ok(members);
	}
	
	@PostMapping("/userCheck")
	public ResponseEntity<?> login(@RequestBody MembersDto membersDto, HttpSession session,
			HttpServletRequest httpServletRequest) {
		String memberId = session.getAttribute("memberId").toString();
		String memberPw = mypageService.getMemberPw(membersDto.getMemberId(), membersDto);

		if(memberPw == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
}

package com.commit.controller;

import java.util.List;
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

import com.commit.entity.Board;
import com.commit.entity.BoardLike;
import com.commit.entity.BoardScrap;
import com.commit.entity.JobScrap;
import com.commit.entity.Members;
import com.commit.entity.NewsComment;
import com.commit.entity.NewsLike;
import com.commit.entity.NewsScrap;
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

	// 마이페이지 메인
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

	// 채용 스크랩
	@GetMapping("/jobScrap")
	public ResponseEntity<?> getScrapJob(HttpSession session, HttpServletRequest request) {
		/*
		 * 1. 세션을 가져와 2. members_Id를 세션에서 받아와 3. memberId(user14)를 서비스에서 가져와(members_Id로
		 * find)
		 */
		session = request.getSession();

		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");

		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<JobScrap> jobScrap = mypageService.getJobsByMemberId(membersId, memberId);

		return ResponseEntity.ok(jobScrap);
	}
	
	// 뉴스 스크랩
	@GetMapping("/newsScrap")
	public ResponseEntity<?> getScrapNews(HttpSession session, HttpServletRequest request) {

		session = request.getSession();
		
		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");
		
		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<NewsScrap> newsScrap = mypageService.getNewsByMemberId(membersId, memberId);
		
		return ResponseEntity.ok(newsScrap);
	}
	
	// 커뮤니티 스크랩
	@GetMapping("/boardScrap")
	public ResponseEntity<?> getScrapBoard(HttpSession session, HttpServletRequest request) {
		
		session = request.getSession();
		
		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");
		
		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<BoardScrap> newsScrap = mypageService.getBoardByMemberId(membersId, memberId);
		
		return ResponseEntity.ok(newsScrap);
	}
	
	// 뉴스 좋아요
	@GetMapping("/myNewsLike")
	public ResponseEntity<?> getLikeNews(HttpSession session, HttpServletRequest request) {

		session = request.getSession();

		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");

		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<NewsLike> newsLike = mypageService.getNewssByMemberId(membersId, memberId);

		return ResponseEntity.ok(newsLike);
	}
	
	// 커뮤니티 좋아요
	@GetMapping("/myBoardLike")
	public ResponseEntity<?> getLikeCommunity(HttpSession session, HttpServletRequest request) {
		
		session = request.getSession();
		
		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");
		
		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<BoardLike> boardLike = mypageService.getCommunityByMemberId(membersId, memberId);
		
		return ResponseEntity.ok(boardLike);
	}
	
	// 내가 쓴 글(커뮤니티)
	@GetMapping("/myCommunity")
	public ResponseEntity<?> getMyContent(HttpSession session, HttpServletRequest request) {
		
		session = request.getSession();
		
		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");
		
		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<Board> boardContent = mypageService.getContentByMemberId(membersId, memberId);
		
		return ResponseEntity.ok(boardContent);
	}

	// 내가 쓴 댓글(뉴스)
	@GetMapping("/myComment")
	public ResponseEntity<?> getMyComment(HttpSession session, HttpServletRequest request) {

		session = request.getSession();
		
		Object membersIdAttribute = session.getAttribute("members_Id");
		Object memberIdAttribute = session.getAttribute("memberId");
		
		// membersIdAttribute 또는 memberIdAttribute가 null인 경우의 처리 코드
		if (membersIdAttribute == null || memberIdAttribute == null) {
			return ResponseEntity.badRequest().body("다시 로그인 해주세요.");
		}
		Integer membersId = (Integer) membersIdAttribute;
		String memberId = memberIdAttribute.toString();
		
		List<NewsComment> newsComment = mypageService.getNewsCommentByMemberId(membersId, memberId);
		
		return ResponseEntity.ok(newsComment);
	}

}

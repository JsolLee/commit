package com.commit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commit.entity.Board;
import com.commit.entity.BoardLike;
import com.commit.entity.BoardScrap;
import com.commit.entity.JobScrap;
import com.commit.entity.Members;
import com.commit.entity.NewsComment;
import com.commit.entity.NewsLike;
import com.commit.entity.NewsScrap;
import com.commit.model.MembersDto;
import com.commit.repository.BoardLikeDao;
import com.commit.repository.BoardScrapDao;
import com.commit.repository.JobScrapDao;
import com.commit.repository.MembersDao;
import com.commit.repository.MypageBoardDao;
import com.commit.repository.MypageDao;
import com.commit.repository.NewsCommentDao;
import com.commit.repository.NewsLikeDao;
import com.commit.repository.NewsScrapDao;

@Service
public class MypageService {
	// 채용 스크랩
	@Autowired
	private JobScrapDao jobScrapDao;

	// 뉴스 스크랩
	@Autowired
	private NewsScrapDao newsScrapDao;
	
	// 보드 스크랩
	@Autowired
	private BoardScrapDao boardScrapDao;
	
	 // 내가 쓴 글(커뮤니티)
	  @Autowired
	  private MypageBoardDao boardDao;
	 
	
	// 내가 쓴 댓글(뉴스)
	@Autowired
	private NewsCommentDao newsCommentDao;
	
	// 좋아요
	@Autowired
	private NewsLikeDao newsLikeDao;

	@Autowired
	private BoardLikeDao boardLikeDao;

	// 마이페이지 메인
	@Autowired
	private MypageDao mypageDao;
	
	@Autowired
	private MembersDao membersDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Optional<Members> getMemberDetails(String memberId){
		Optional<Members> members = mypageDao.findAllByMemberId(memberId);
		
		return members;
	}
	
	public String getMemberPw(String memberId, MembersDto membersDto){
		Optional<Members> members = mypageDao.findByMemberId(memberId);
		
		String memberPw = membersDto.getMemberPw();
		String encodePw = members.get().getMemberPw();
		boolean test = bCryptPasswordEncoder.matches(memberPw, encodePw);
		if(test == false) {
			return null;
		}
		return memberPw;
	}
	
	public String getNickName(String currentName) {
		Optional<Members> members = mypageDao.findByNickName(currentName);
		String nickName = members.get().getNickName();
		
		return nickName;
	}
	
	public String setNewNickName(String currentName, String newName) {
		Members member = membersDao.findByNickName(currentName);
		member.setNickName(newName);
		String newNickName = member.getNickName();
		
		return newNickName;
	}
	
	public boolean getMemberById(String memberId, String memberPw, String changePw){
		Optional<Members> members = mypageDao.findByMemberId(memberId);
//		String changePw2 = bCryptPasswordEncoder.encode(changePw);
		
		boolean test = bCryptPasswordEncoder.matches(changePw, members.get().getMemberPw());
		
		return test;
	}
	
	public String updateMemberPw(String memberId, String changePw) {
		String encodedPw = bCryptPasswordEncoder.encode(changePw);

		Optional<Members> member = membersDao.findByMemberId(memberId);
		member.get().setMemberPw(encodedPw);
		
		String newMemberPw = member.get().getMemberPw();
		
		return newMemberPw;
	}
	
	public String setMemberOut(String memberId, String memberExit) {
		Optional<Members> member = mypageDao.findByMemberId(memberId);
		member.get().setMemberOut(memberExit);
		String memberOut = member.get().getMemberOut();
		
		return memberOut;
	}

	// 채용 스크랩
	public List<JobScrap> getJobsByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<JobScrap> jobScrap = jobScrapDao.findByMembers_Id(membersId);
		
		return jobScrap;
	}
	
	// 뉴스 스크랩
	public List<NewsScrap> getNewsByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<NewsScrap> newsScrap = newsScrapDao.findByMembers_Id(membersId);
		
		return newsScrap;
	}
	
	// 커뮤니티 스크랩
	public List<BoardScrap> getBoardByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<BoardScrap> boardScrap = boardScrapDao.findByMembers_Id(membersId);
		
		return boardScrap;
	}
	
	// 뉴스 좋아요
	public List<NewsLike> getNewssByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<NewsLike> newsLike = newsLikeDao.findByMembers_Id(membersId);
		
		return newsLike;
	}
	
	// 커뮤니티 좋아요
	public List<BoardLike> getCommunityByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<BoardLike> boardLike = boardLikeDao.findByMembers_Id(membersId);
		
		return boardLike;
	}
	
	// 내가 쓴 글(커뮤니티)
	public List<Board> getContentByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<Board> boardContent = boardDao.findByMember_Id(membersId);
		
		return boardContent;
	}
	
	// 내가 쓴 댓글(뉴스)
	public List<NewsComment> getNewsCommentByMemberId(Integer membersId, String memberId){
		Optional<Members> member = mypageDao.findByIdAndMemberId(membersId, memberId);
		
		List<NewsComment> newsComment = newsCommentDao.findByMember_Id(membersId);
		
		return newsComment;
	}
}

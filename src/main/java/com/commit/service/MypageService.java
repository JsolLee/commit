package com.commit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.repository.MembersDao;
import com.commit.repository.MypageDao;

@Service
public class MypageService {
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

	
}

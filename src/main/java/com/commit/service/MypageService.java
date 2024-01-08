package com.commit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.repository.MypageDao;

@Service
public class MypageService {
	@Autowired
	private MypageDao mypageDao;
	
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
}

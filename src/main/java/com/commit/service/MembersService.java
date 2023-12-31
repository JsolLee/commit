package com.commit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.commit.entity.Members;
import com.commit.model.MembersDto;
import com.commit.repository.MembersDao;

@Service
public class MembersService {
	@Autowired
	private MembersDao membersDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public MembersService(MembersDao membersDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.membersDao = membersDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	public void join(MembersDto membersDto) {
		Members members = new Members();
		//System.out.println("service :" + membersDto);
		members.setRole("ROLE_USER");
		members.setMemberId(membersDto.getMemberId());
		//System.out.println("service :" + membersDto.getMemberPw());
		String rawPassword = membersDto.getMemberPw();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        //String password = bCryptPasswordEncoder.encode(membersDto.getMemberPw());
        members.setMemberPw(encPassword);
        members.setEmail(membersDto.getEmail());
        members.setNickName(membersDto.getNickName());
        members.setMemberOut("N");
        
        System.out.println("service :" + membersDto);
        membersDao.save(members);
    }

	public Optional<Members> findByMemberIdAndMemberPw(MembersDto membersDto) {
		String memberId = membersDto.getMemberId();
		String memberPw = membersDto.getMemberPw();

		Optional<Members> members = membersDao.findByMemberId(memberId);
				
		if (members.isPresent()) {
			String encodePw = members.get().getMemberPw();
			boolean test = bCryptPasswordEncoder.matches(memberPw, encodePw);
			if(test) {
				members = membersDao.findByMemberIdAndMemberPw(memberId, encodePw);
				return members;
			}
		}
        return Optional.empty(); // null을 리턴할 경우 nullPointException 에러를 일으킬 수 있음
	}
	
}

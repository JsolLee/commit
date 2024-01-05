package com.commit.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.commit.entity.Members;
import com.commit.repository.MembersDao;

@Service
public class MembersDetailsService implements UserDetailsService{
	
	@Autowired
	private MembersDao membersDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Members memberEntity = membersDao.findByMemberId(memberId);
		System.out.println("memberEntity : " + memberEntity);
		if(memberEntity != null) {
			return new MembersDetails(memberEntity);
		}
		return null;
	}
	*/
}

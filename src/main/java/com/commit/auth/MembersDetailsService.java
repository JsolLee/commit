package com.commit.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.commit.entity.Members;
import com.commit.repository.MembersDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersDetailsService implements UserDetailsService{
   
   
   private final MembersDao membersDao;   
   
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Members member = membersDao.findByMemberId(username)
         .orElseThrow(()->{
            return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
         });
      return new MembersDetails(member);
   }
}
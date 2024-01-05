package com.commit.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.commit.entity.Members;
import com.commit.model.MembersDto;

import lombok.Getter;

@Getter
public class MembersDetails implements UserDetails {
	// Member 정보를 가지고 있는 애, membersDto 준비
	private Members members;
	
	// 생성자 일반 로그인
	public MembersDetails(Members members) {
		this.members = members;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return members.getRole();
			}
		});
//		System.out.println("collect : " + collect);
		return collect;
	}

	@Override
	public String getPassword() {
		return members.getMemberPw();
	}

	@Override
	public String getUsername() {
		return members.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 만료되지 않은 계정인가?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 잠기지 않은 계정인가?
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 사용 기한이 지나지 않았는가?
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 이 계정이 활성화 되어 있는가? (휴면 계정이 아닌가?)
		return true;
	}

}

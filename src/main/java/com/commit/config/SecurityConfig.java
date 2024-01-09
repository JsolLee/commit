package com.commit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.commit.auth.MemberAuthenticationSuccessHandler;
import com.commit.service.HistoryService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig{
	
	private final HistoryService historyService;
	
	public SecurityConfig(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	// 로그인 성공시 동작하는 UserAuthenticationSuccessHandler 핸들러 추가
    @Bean
    MemberAuthenticationSuccessHandler getSuccessHandler() {
        return new MemberAuthenticationSuccessHandler(historyService);
    }
	// 접속하는 사용자들이 걸러주는것
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
			//if(로그인해야하는게 많을때는 .anyRequest().permitAll()하구 인증받을페이지만 입력할 수 있도록
			//람다함수 이용
		/*
			http.authorizeHttpRequests(requests -> 
				requests.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/", "/joinForm", "/loginForm", "/join", "/login").permitAll() //누구나 다 접근할 수 있게 하겠다.
					.anyRequest()
					//.authenticated()//인증받은 사람들만
			)
			*/
			http.authorizeHttpRequests(requests -> 
				requests.anyRequest().permitAll() //누구나 다 접근할 수 있게 하겠다.
				)	
			.formLogin(login -> 
				login
				.usernameParameter("memberId")
				.passwordParameter("memberPw")
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(getSuccessHandler())
				.defaultSuccessUrl("/")
				.failureUrl("/login")
			)
			.logout(logout ->
				logout.logoutSuccessUrl("/")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
			)
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable());
			return http.build();
	}
}

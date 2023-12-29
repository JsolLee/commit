package com.commit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
	/* 자동으로 주입받을 수 있게끔 -> BCryptPasswordEncoder : 패스워드를 암호화해주는
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		// 객체를주입받을수있게준비를해둠
		return new BCryptPasswordEncoder();
	}
	 */
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
				login.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
			)
			.logout(logout ->
				logout.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			)
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable());
			return http.build();
	}
}

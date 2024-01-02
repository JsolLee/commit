package com.commit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commit.model.MembersDto;
import com.commit.service.MembersService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody MembersDto membersDto) {
		membersService.join(membersDto);
        return ResponseEntity.ok().build();
	}	
}

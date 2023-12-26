package com.commit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commit.entity.Members;

public interface MembersDao extends JpaRepository<Members, Integer>{
	//public Members findByUsername(String username);
}

package com.commit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commit.entity.Members;

import org.springframework.stereotype.Repository;

@Repository
public interface MembersDao extends JpaRepository<Members, Integer>{
	//public Members findByUsername(String username);
}

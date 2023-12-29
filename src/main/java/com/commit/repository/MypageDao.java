package com.commit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.Members;

@Repository
public interface MypageDao extends JpaRepository<Members, Integer> {

}

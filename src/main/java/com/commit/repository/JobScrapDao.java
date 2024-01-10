package com.commit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.JobScrap;
import com.commit.entity.Members;

@Repository
public interface JobScrapDao extends JpaRepository<JobScrap, Integer> {
	// 채용 스크랩
	List<JobScrap> findByMembers_Id(Integer membersId);
}

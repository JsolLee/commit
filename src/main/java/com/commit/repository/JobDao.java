package com.commit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.commit.entity.Job;

@Repository
public interface JobDao extends JpaRepository<Job, Integer>{

    // 채용 상세 조회수 증가
	@Transactional(isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Query("UPDATE Job j SET j.viewcount = j.viewcount + 1 WHERE j.id = :id")
    int incrementViewCount(@Param("id") Integer id);

}

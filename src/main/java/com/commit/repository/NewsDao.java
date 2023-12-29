package com.commit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.commit.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {
    // 필요한 JPA 메소드 추가 (예: findByTitle, findAllByCategory 등)
}
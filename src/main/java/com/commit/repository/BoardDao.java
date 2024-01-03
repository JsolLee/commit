package com.commit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.entity.Board;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer>{

}
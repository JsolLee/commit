package com.commit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commit.entity.Job;
import com.commit.repository.JobDao;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;

	public List<Job> getAllJobs() {
		List<Job> list = jobDao.findAll();
		return list;
	}
//
//	private Job convertToDto(Job job) {
//		return Job.builder()
//				.id(job.getId())
//				.title(job.getTitle())
//				.build();
//	}
}

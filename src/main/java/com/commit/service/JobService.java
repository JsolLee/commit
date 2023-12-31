package com.commit.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commit.entity.Job;
import com.commit.model.JobDto;
import com.commit.repository.JobDao;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;

	public List<Job> getAllJobs() {
		List<Job> list = jobDao.findAll();
		return list;
	}

}

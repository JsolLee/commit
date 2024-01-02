package com.commit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.commit.entity.Job;
import com.commit.model.JobDto;
import com.commit.service.JobService;

@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/Job")
	public Page<Job> result(@PageableDefault(size=10, sort="id", direction = Sort.Direction.ASC) Pageable pageable){
		Page<Job> result = jobService.getPages(pageable);
		return result;
	}

    @GetMapping("/Job/JobView/{id}")
    public JobDto getNewsById(@PathVariable(name = "id") Integer id) {
        return jobService.getJobById(id);

    }
}
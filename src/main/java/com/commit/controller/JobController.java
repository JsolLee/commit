package com.commit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ArrayList<Job> list(Job job) {

        // DB에 있는 데이터 조회
        ArrayList<Job> list = (ArrayList<Job>) jobService.getAllJobs();
        return list;
    }

    @GetMapping("/Job/JobView/{id}")
    public JobDto getNewsById(@PathVariable(name = "id") Integer id) {
        return jobService.getJobById(id);

    }
}
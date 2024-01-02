package com.commit.service;

import java.util.List;
import java.util.Optional;

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

    // 채용 가져오기 : id로 가져오기
    public JobDto getJobById(Integer id) {
        Optional<Job> job = jobDao.findById(id);
        if(job.isPresent()) {return convertToDto(job.get());}
        return null;
    }

    // json type으로 컨버팅
    private JobDto convertToDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .career(job.getCareer())
                .degree(job.getDegree())
                .location(job.getLocation())
                .image(job.getImage())
                .viewcount(job.getViewcount())
                .createDate(job.getCreateDate())
                .finishDate(job.getFinishDate_D())
                .build();
    }

}
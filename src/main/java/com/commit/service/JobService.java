package com.commit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.commit.entity.Job;
import com.commit.model.JobDto;
import com.commit.repository.JobDao;

@Service
public class JobService {
    @Autowired
    private JobDao jobDao;
	
	// 페이지 시작 : 0, 한 페이지에 보여줄 개수 : 10
	Pageable pageable = PageRequest.of(0, 10);
	
	public Page<Job> getPages(Pageable pageable) {
		return jobDao.findAll(pageable);
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
                .companyname(job.getCompanyname())
                .location(job.getLocation())
                .image(job.getImage())
                .viewcount(job.getViewcount())
                .createDate(job.getCreateDate())
                .finishDate(job.getFinishDate_D())
                .size(job.getSize())
                .type(job.getType())
                .salary(job.getSalary())
                .page(job.getPage())
                .location2(job.getLocation2())
                .content(job.getContent())
                .build();
    }
    
    // 채용 조회수 가져오기
    public void incrementJobView(Integer id) {
    	jobDao.incrementViewCount(id);
    }
}
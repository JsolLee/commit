package com.commit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.commit.entity.News;
import com.commit.model.NewsDto;
import com.commit.repository.NewsDao;

import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    public NewsDto getNewsById(Integer id) {
        Optional<News> news = newsDao.findById(id);
        if(news.isPresent()) {
            return convertToDto(news.get());
        }
        return null;
    }

    private NewsDto convertToDto(News news) {
    	return NewsDto.builder()
                .id(news.getId())
                .category(news.getCategory())
                .title(news.getTitle())
                .subtitle(news.getSubtitle())
                .content(news.getContent())
                .origin(news.getOrigin())
                .image(news.getImage())
                .writer(news.getWriter())
                .viewcount(news.getViewcount())
                .likecount(news.getLikecount())
                .originDate(news.getOriginDate())
                .createDate(news.getCreateDate())
                .build();
    }
}

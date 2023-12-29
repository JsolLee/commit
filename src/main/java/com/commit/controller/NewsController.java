package com.commit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.commit.model.NewsDto;
import com.commit.service.NewsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news/{id}")
    public NewsDto getNewsById(@PathVariable(name = "id") Integer id) {
        return newsService.getNewsById(id);
    }
}

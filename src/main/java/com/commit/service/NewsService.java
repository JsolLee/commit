package com.commit.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.commit.entity.Board;
import com.commit.entity.News;
import com.commit.model.NewsDto;
import com.commit.repository.NewsDao;

@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;

	// 페이지네이션
	Pageable pageable = PageRequest.of(0, 10);

	public Page<News> getPages(Pageable pageable) {
		return newsDao.findAll(pageable);
	}

	// NewsList
	// 카테고리별로 가져오기
    public List<NewsDto> getNewsByCategory(String category) {
    	// 1. Dao에서 카테고리 spl 가져오기
        return newsDao.findByCategoryOrderByCreateDateDesc(category).stream()
                // 2. map으로 NewsDto의 convertToDto으로 전환해주기
        		.map(NewsDto::convertToDto)
        		// 3. collect으로 리스트로 보내기
                .collect(Collectors.toList());
    }
    
    // 카테고리 메인뉴스 가져오기
    public NewsDto getTopNewsByCategory(String category) {
    	// 1. 
        Page<News> newsPage = newsDao.findTopByCategoryOrderByLikecountDescViewcountDesc(category, PageRequest.of(0, 1));
        return newsPage.hasContent() ? NewsDto.convertToDto(newsPage.getContent().get(0)) : null;
    }
    
    //NewsView
    // 뉴스 가져오기 : id로 가져오기
    public NewsDto getNewsById(Integer id) {
    	// 1. id로 DB 조회
        Optional<News> news = newsDao.findById(id);
        // 2. DB 조회 갯수 null check
        if(news.isPresent()) {return NewsDto.convertToDto(news.get());}
        // 3. news를 DTO로 변경하 리턴
        return null;
    }
    
    // 인기 뉴스 가져오기
    public List<NewsDto> getPopularNews() {
        return newsDao.findTop6ByOrderByViewcountDesc().stream()
                .map(NewsDto::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 최신 뉴스 목록 가져오기
    public List<NewsDto> getLatestNews() {
        return newsDao.findTop6ByOrderByCreateDateDesc().stream()
                .map(NewsDto::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 관련 뉴스 가져오기
    public List<NewsDto> getRelatedNews(String category) {
        return newsDao.findTop6ByCategoryOrderByCreateDateDesc(category).stream()
                .map(NewsDto::convertToDto)
                .collect(Collectors.toList());
    }
    
    // 뉴스 조회수 가져오기
    public void incrementNewsView(Integer id) {
        newsDao.incrementViewCount(id);
    }
    
    // NewsMain

    // 조회수로 1,2,3번째 뉴스 가져오기
    public List<News> getFirstNews() {
       List<News> newsList = newsDao.findTop7ByOrderByViewcountDesc();
       return newsList;
    }

    // 조회수로 4,5번째 뉴스 가져오기
//     public List<News> getFourFiveNews() {
//         List<News> newsList = newsDao.findByOrderByViewcountDesc();
//         if (newsList.size() >= 5) {
//             List<News> selectedNewsList = new ArrayList<>();
//             News fourthNews = newsList.get(3); // 4번째로 많은 뉴스
//             News fifthNews = newsList.get(4); // 5번째로 많은 뉴스
//             selectedNewsList.add(fourthNews);
//             selectedNewsList.add(fifthNews);
//             return selectedNewsList;
//             // 가져온 4번째와 5번째 뉴스를 활용합니다.
//         } else {
//             // 뉴스가 5개보다 적을 경우 처리할 내용을 작성합니다.
//             return null;
//         }
//     }

    // 조회수로 6,7번째 뉴스 가져오기
//     public List<News> getSixSevenNews() {
//         List<News> newsList = newsDao.findByOrderByViewcountDesc();
//         if (newsList.size() >= 7) {
//             List<News> selectedNewsList = new ArrayList<>();
//             News fourthNews = newsList.get(5); // 6번째로 많은 뉴스
//             News fifthNews = newsList.get(6); // 7번째로 많은 뉴스
//             selectedNewsList.add(fourthNews);
//             selectedNewsList.add(fifthNews);
//             return selectedNewsList;
//             // 가져온 6번째와 7번째 뉴스를 활용합니다.
//         } else {
//             // 뉴스가 7개보다 적을 경우 처리할 내용을 작성합니다.
//             return null;
//         }
//     }

    // 각 카테고리마다 3개의 뉴스를 가져옴
    public List<News> getCategoryNews() {
        List<News> infotechList = newsDao.findTop3ByCategoryOrderByCreateDateDesc("infotech");
        List<News> companyList = newsDao.findTop3ByCategoryOrderByCreateDateDesc("company");
        List<News> conferenceList = newsDao.findTop3ByCategoryOrderByCreateDateDesc("conference");
        
        List<News> allBoardList = new ArrayList<>();
        Set<News> existingNewsSet = new HashSet<>();

        // getFirstNews()에서 가져온 데이터와 겹치지 않는 뉴스 추가
        List<News> firstNewsList = getFirstNews();
        existingNewsSet.addAll(firstNewsList);

        for (News news : infotechList) {
            if (!existingNewsSet.contains(news)) {
                allBoardList.add(news);
                existingNewsSet.add(news);
            }
        }

        for (News news : companyList) {
            if (!existingNewsSet.contains(news)) {
                allBoardList.add(news);
                existingNewsSet.add(news);
            }
        }

        for (News news : conferenceList) {
            if (!existingNewsSet.contains(news)) {
                allBoardList.add(news);
                existingNewsSet.add(news);
            }
        }

        return allBoardList;
    }

    // 뉴스 가져오기 : id로 가져오기
    public NewsDto getNewsId(Integer id) {
       Optional<News> news = newsDao.findById(id);
       if (news.isPresent()) {
          return convertToDto(news.get());
       }
       return null;
    }

    private NewsDto convertToDto(News news) {
       return NewsDto.builder().id(news.getId()).likecount(news.getLikecount()).viewcount(news.getViewcount())
             .createDate(news.getCreateDate()).originDate(news.getOriginDate()).category(news.getCategory())
             .content(news.getContent()).deleteYN(news.getDeleteYN()).image(news.getImage())
             .subtitle(news.getSubtitle()).title(news.getTitle()).writer(news.getWriter()).origin(news.getOrigin())
             .build();
    }
 }

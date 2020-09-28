package com.opm.controller;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opm.model.ErrorLog;
import com.opm.model.News;
import com.opm.model.WebServiceResult;
import com.opm.repository.ErrorLogRepository;
import com.opm.repository.NewsRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("news")
public class NewsController {
	@Autowired
	NewsRepository newsRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;
	 
	Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@PostMapping(path = "/createNews")
	public WebServiceResult createNews(@ModelAttribute News news) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("createNews() started");
		try {
			news.setImg(news.getImgFile().getBytes());
			news.setCreatedDateTime(new Date());
			newsRepository.save(news);
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("createNews() ended successfully.");			
		}catch (Exception e) {
			wsr.setMsg(e.getMessage());
			wsr.setStatus(false);
			wsr.setE(e);
			try {
				ErrorLog errLog = new ErrorLog();
				errLog.setMsg(e.getMessage());
				errLog.setCreatedDateTime(new Date());
				errorLogRepository.save(errLog);
			}catch(Exception e2) {
				//swallow exception for normal flow
			}
			logger.info("createJob() ended with exception - " + e.getMessage());	
		}

		return wsr;		
	}
	
	
	@GetMapping("/getNews")
	public List<News> getNewsList() {
		logger.info("getNewsList() started.");		
		
		List<News> newsList = newsRepository.findFirst6ByOrderByCreatedDateTimeDesc();
		
		logger.info("getNewsList() ended with NewsList size - " + newsList.size());
		return newsList;
	}
	
	@PostMapping(path = "/deleteNews")
	public WebServiceResult deleteNews(long id) {
		
		logger.info("deleteNews() started with newsId - " + id);
		WebServiceResult wsr = new WebServiceResult();
		
		News news = newsRepository.findById(id).orElse(null);
		if (news != null) {
			try {
				news.setExpiryDate(new Date());
				newsRepository.save(news);
				wsr.setMsg(WebServiceResult.SUCCESS);
				wsr.setStatus(true);
				logger.info("deleteNews() ended successfully.");			
			}catch (Exception e) {
				wsr.setMsg(e.getMessage());
				wsr.setStatus(false);
				wsr.setE(e);
				try {
					ErrorLog errLog = new ErrorLog();
					errLog.setMsg(e.getMessage());
					errLog.setCreatedDateTime(new Date());
					errorLogRepository.save(errLog);
				}catch(Exception e2) {
					//swallow exception for normal flow
				}
				logger.info("deleteNews() ended with exception - " + e.getMessage());	
			}						
		}else {
			wsr.setMsg(WebServiceResult.FAILED);
			wsr.setStatus(false);
			logger.info("deleteNews() ended, no news associate with id - " + id);	
		}
		

		return wsr;		
	}
}

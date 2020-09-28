package com.opm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opm.model.ErrorLog;
import com.opm.model.JobApplicantInfo;
import com.opm.model.JobVacancy;
import com.opm.model.WebServiceResult;
import com.opm.repository.ErrorLogRepository;
import com.opm.repository.JobApplicantInfoRepository;
import com.opm.repository.JobVacancyRepository;
import com.opm.util.EmailUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("job")
public class JobVacanciesController {
	@Autowired
	JobApplicantInfoRepository jobApplicantInfoRepository;
	
	@Autowired
	JobVacancyRepository jobVacancyRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;
	 
	Logger logger = LoggerFactory.getLogger(JobVacanciesController.class);
	
	@PostMapping(path = "/createJob")
	public WebServiceResult createJob(@ModelAttribute JobVacancy jobVacancy) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("createJob() started");
		try {
			if (jobVacancy.getSeq() == 0) {
				jobVacancy.setSeq(100);
			}
			jobVacancy.setCreatedDateTime(new Date());
			jobVacancyRepository.save(jobVacancy);
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("createJob() ended successfully.");			
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
	
	
	@GetMapping("/getCurrentJobVacancies")
	public List<JobVacancy> getCurrentJobVacancies() {
		logger.info("getCurrentJobVacancies() started.");
		
		List<JobVacancy> jobVacanciesList = jobVacancyRepository.findAll(Sort.by("seq").ascending().and(Sort.by("createdDateTime").descending()));

		logger.info("getCurrentJobVacancies() ended with jobVacanciesList - " + jobVacanciesList);
		return jobVacanciesList;
	}
	
	@PostMapping(path = "/applyJob")
	public WebServiceResult applyJob(@ModelAttribute JobApplicantInfo jobApplicantInfo) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("applyJob() started with jobApplicantInfo - " + jobApplicantInfo);
		if (jobApplicantInfo.getName() == null || jobApplicantInfo.getName().isEmpty() || 
				jobApplicantInfo.getEmail() == null || jobApplicantInfo.getEmail().isEmpty() || 
			    jobApplicantInfo.getResume() == null || jobApplicantInfo.getResume().isEmpty()) {
			wsr.setMsg("Missing Name / Email / Resume.");
			wsr.setStatus(false);
			return wsr;
		}
		
		try {	
			jobApplicantInfo.setResumeByteArray(jobApplicantInfo.getResume().getBytes());
			jobApplicantInfo.setResumeName(jobApplicantInfo.getResume().getOriginalFilename());
			jobApplicantInfo.setCreatedDateTime(new Date());
			jobApplicantInfoRepository.save(jobApplicantInfo);
			
			EmailUtil.sendEmail(jobApplicantInfo.getEmail().trim());
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("applyJob() ended successfully.");			
		}catch (IOException e) {
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
			
			logger.info("applyJob() ended with exception - " + e.getMessage());
		}
		
		return wsr;
	}
	
	@PostMapping(path = "/deleteJob")
	public WebServiceResult deleteJob(long id) {
		logger.info("deleteJob() started with jobVacancy Id - " + id);
		
		WebServiceResult wsr = new WebServiceResult();
		
		JobVacancy jobVacancy = jobVacancyRepository.findById(id).orElse(null);
		
		if (jobVacancy != null) {
			try {
				jobVacancy.setExpiryDate(new Date());
				jobVacancyRepository.save(jobVacancy);
				wsr.setMsg(WebServiceResult.SUCCESS);
				wsr.setStatus(true);
				logger.info("deleteJob() ended successfully.");			
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
				logger.info("deleteJob() ended with exception - " + e.getMessage());	
			}
		}else {
			wsr.setMsg(WebServiceResult.FAILED);
			wsr.setStatus(false);
			logger.info("deleteJob() ended, no jobVacancy associate with id - " + id);			
		}
		

		return wsr;		
	}
}

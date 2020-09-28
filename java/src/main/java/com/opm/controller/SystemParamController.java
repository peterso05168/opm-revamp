package com.opm.controller;

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
import com.opm.model.Image;
import com.opm.model.SystemParam;
import com.opm.model.SystemParamWithImageList;
import com.opm.model.WebServiceResult;
import com.opm.repository.ErrorLogRepository;
import com.opm.repository.ImageRepository;
import com.opm.repository.SystemParamRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("systemParams")
public class SystemParamController {
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	SystemParamRepository systemParamRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;
	 
	Logger logger = LoggerFactory.getLogger(SystemParamController.class);
	
	@PostMapping(path = "/createSystemParam")
	public WebServiceResult createSystemParam(@ModelAttribute SystemParam systemParam) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("createSystemParam() started");
		try {
			systemParam.setCreatedDateTime(new Date());
			systemParamRepository.save(systemParam);
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("createSystemParam() ended successfully.");			
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
			logger.info("createSystemParam() ended with exception - " + e.getMessage());	
		}

		return wsr;		
	}
	
	@GetMapping("/getSystemParamWithImageList")
	public SystemParamWithImageList getSystemParamWithImageList(String code) {
		logger.info("getSystemParamWithImageList() started.");
		SystemParamWithImageList systemParamWithImageList = new SystemParamWithImageList();
		if (code != null) {
			SystemParam systemParam = systemParamRepository.findByCode(code);
			List<Image> imageList = imageRepository.findByCode(code);
			
			systemParamWithImageList.setSystemParam(systemParam);
			systemParamWithImageList.setImageList(imageList);
		}
		
		logger.info("getSystemParamWithImageList() ended");
		return systemParamWithImageList;
	}
	
	
	@GetMapping("/getSystemParamList")
	public List<SystemParam> getSystemParamList() {
		logger.info("getSystemParamList() started.");
		
		List<SystemParam> systemParamList = systemParamRepository.findAll(Sort.by("createdDateTime").descending());

		logger.info("getSystemParamList() ended with PartnersList size - " + systemParamList.size());
		return systemParamList;
	}
	
	@PostMapping(path = "/deleteSystemParam")
	public WebServiceResult deleteSystemParam(long id) {
		
		logger.info("deleteSystemParam() started with systemParam Id - " + id);
		WebServiceResult wsr = new WebServiceResult();
		
		SystemParam systemParam = systemParamRepository.findById(id).orElse(null);
		if (systemParam != null) {
			try {
				systemParam.setExpiryDate(new Date());
				systemParamRepository.save(systemParam);
				wsr.setMsg(WebServiceResult.SUCCESS);
				wsr.setStatus(true);
				logger.info("deleteSystemParam() ended successfully.");			
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
				logger.info("deleteSystemParam() ended with exception - " + e.getMessage());	
			}
		}else {
			wsr.setMsg(WebServiceResult.FAILED);
			wsr.setStatus(false);
			logger.info("deleteSystemParam() ended, no systemParam associate with id - " + id);			
		}
		

		return wsr;		
	}
}

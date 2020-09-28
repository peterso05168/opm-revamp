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
import com.opm.model.Service;
import com.opm.model.WebServiceResult;
import com.opm.repository.ErrorLogRepository;
import com.opm.repository.ServiceRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("services")
public class ServiceController {
	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;
	 
	Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@PostMapping(path = "/createService")
	public WebServiceResult createService(@ModelAttribute Service service) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("createService() started");
		try {
			service.setImg(service.getImgFile().getBytes());
			service.setCreatedDateTime(new Date());
			serviceRepository.save(service);
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("createService() ended successfully.");			
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
			logger.info("createService() ended with exception - " + e.getMessage());	
		}

		return wsr;		
	}
	
	@GetMapping("/getServiceList")
	public List<Service> getServiceList() {
		logger.info("getServiceList() started.");		
		
		List<Service> serviceList = serviceRepository.findAll();
		
		logger.info("getServiceList() ended with serviceList size - " + serviceList.size());
		return serviceList;
	}
	
	@GetMapping("/getServiceListWithNameAndId")
	public List<Service> getServiceListWithNameAndId() {
		logger.info("getServiceListWithNameAndId() started.");		
		
		List<Service> serviceList = serviceRepository.findAll();
		serviceList.stream().forEach(service -> service.setOnlyNameAndId());
		
		logger.info("getServiceListWithNameAndId() ended with serviceList size - " + serviceList.size());
		return serviceList;
	}
	
	@PostMapping(path = "/deleteService")
	public WebServiceResult deleteService(long id) {
		
		logger.info("deleteService() started with service Id - " + id);
		WebServiceResult wsr = new WebServiceResult();
		
		Service service = serviceRepository.findById(id).orElse(null);
		if (service != null) {
			try {
				service.setExpiryDate(new Date());
				serviceRepository.save(service);
				wsr.setMsg(WebServiceResult.SUCCESS);
				wsr.setStatus(true);
				logger.info("deleteService() ended successfully.");			
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
				logger.info("deleteService() ended with exception - " + e.getMessage());	
			}						
		}else {
			wsr.setMsg(WebServiceResult.FAILED);
			wsr.setStatus(false);
			logger.info("deleteServices() ended, no services associate with id - " + id);	
		}
		

		return wsr;		
	}
}

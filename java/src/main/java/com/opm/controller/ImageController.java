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
import com.opm.model.WebServiceResult;
import com.opm.repository.ErrorLogRepository;
import com.opm.repository.ImageRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("images")
public class ImageController {
	@Autowired
	ImageRepository imageRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;
	 
	Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@PostMapping(path = "/createImage")
	public WebServiceResult createImage(@ModelAttribute Image image) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("createImage() started");
		try {
			image.setImg(image.getImgFile().getBytes());
			image.setCreatedDateTime(new Date());
			imageRepository.save(image);
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("createImage() ended successfully.");			
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
			logger.info("createImage() ended with exception - " + e.getMessage());	
		}

		return wsr;		
	}
	
	
	@GetMapping("/getImageList")
	public List<Image> getImageList() {
		logger.info("getImageList() started.");
		
		List<Image> imageList = imageRepository.findAll(Sort.by("createdDateTime").descending());

		logger.info("getImageList() ended with size - " + imageList.size());
		return imageList;
	}
	
	@PostMapping(path = "/deleteImage")
	public WebServiceResult deleteImage(long id) {
		
		logger.info("deleteImage() started with image Id - " + id);
		WebServiceResult wsr = new WebServiceResult();
		
		Image image = imageRepository.findById(id).orElse(null);
		if (image != null) {
			try {
				image.setExpiryDate(new Date());
				imageRepository.save(image);
				wsr.setMsg(WebServiceResult.SUCCESS);
				wsr.setStatus(true);
				logger.info("deleteImage() ended successfully.");			
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
				logger.info("deleteImage() ended with exception - " + e.getMessage());	
			}
		}else {
			wsr.setMsg(WebServiceResult.FAILED);
			wsr.setStatus(false);
			logger.info("deleteImage() ended, no image associate with id - " + id);			
		}
		

		return wsr;		
	}
}

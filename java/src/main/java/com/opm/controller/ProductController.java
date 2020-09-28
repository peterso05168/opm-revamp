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
import com.opm.model.Product;
import com.opm.model.WebServiceResult;
import com.opm.repository.ErrorLogRepository;
import com.opm.repository.ProductRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ErrorLogRepository errorLogRepository;
	 
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping(path = "/createProduct")
	public WebServiceResult createProduct(@ModelAttribute Product product) {
		WebServiceResult wsr = new WebServiceResult();
		logger.info("createProduct() started");
		try {
			product.setImg(product.getImgFile().getBytes());
			product.setCreatedDateTime(new Date());
			productRepository.save(product);
			wsr.setMsg(WebServiceResult.SUCCESS);
			wsr.setStatus(true);
			logger.info("createProduct() ended successfully.");			
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
			logger.info("createProduct() ended with exception - " + e.getMessage());	
		}

		return wsr;		
	}
	
	
	@GetMapping("/getProductList")
	public List<Product> getProductList() {
		logger.info("getProductList() started.");		
		
		List<Product> productList = productRepository.findAll();
		
		logger.info("getProductList() ended with getProductList size - " + productList.size());
		return productList;
	}
	
	@GetMapping("/getProductListWithNameAndId")
	public List<Product> getProductListWithNameAndId() {
		logger.info("getProductListWithNameAndId() started.");		
		
		List<Product> productList = productRepository.findAll();
		productList.stream().forEach(product -> product.setOnlyNameAndId());
		
		logger.info("getProductListWithNameAndId() ended with getProductList size - " + productList.size());
		return productList;
	}
	
	@GetMapping("/getProductListByServiceId")
	public List<Product> getProductListByServiceId(long id) {
		logger.info("getProductListByServiceId() started.");		
		
		List<Product> productList = productRepository.findByServiceId(id);

		logger.info("getProductListByServiceId() ended with productList size - " + productList.size());
		return productList;	
	}
	
	@PostMapping(path = "/deleteProduct")
	public WebServiceResult deleteProduct(long id) {
		
		logger.info("deleteProduct() started with product Id - " + id);
		WebServiceResult wsr = new WebServiceResult();
		
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {
			try {
				product.setExpiryDate(new Date());
				productRepository.save(product);
				wsr.setMsg(WebServiceResult.SUCCESS);
				wsr.setStatus(true);
				logger.info("deleteProduct() ended successfully.");			
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
				logger.info("deleteProduct() ended with exception - " + e.getMessage());	
			}						
		}else {
			wsr.setMsg(WebServiceResult.FAILED);
			wsr.setStatus(false);
			logger.info("deleteProduct() ended, no product associate with id - " + id);	
		}
		

		return wsr;		
	}
}

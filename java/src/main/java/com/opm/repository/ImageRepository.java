package com.opm.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.Image;

 
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long>{  
	public List<Image> findByCode(String code);
}
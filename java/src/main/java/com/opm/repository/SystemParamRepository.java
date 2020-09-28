package com.opm.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.SystemParam;

 
@Transactional
public interface SystemParamRepository extends JpaRepository<SystemParam, Long>{  
	public SystemParam findByCode(String code);
}
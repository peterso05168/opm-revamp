package com.opm.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.ErrorLog;

 
@Transactional
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long>{  

}
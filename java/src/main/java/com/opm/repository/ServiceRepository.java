package com.opm.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.Service;

 
@Transactional
public interface ServiceRepository extends JpaRepository<Service, Long>{  

}
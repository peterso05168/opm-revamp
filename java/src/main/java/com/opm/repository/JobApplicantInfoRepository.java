package com.opm.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.JobApplicantInfo;
 
 
@Transactional
public interface JobApplicantInfoRepository extends JpaRepository<JobApplicantInfo, Long>{  
  public JobApplicantInfo findByName(String name);
}
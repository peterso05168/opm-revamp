package com.opm.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.JobVacancy;

 
@Transactional
public interface JobVacancyRepository extends JpaRepository<JobVacancy, Long>{  
  public JobVacancy findByJobTitle(String jobTitle);
}
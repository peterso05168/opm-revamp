package com.opm.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.News;

 
@Transactional
public interface NewsRepository extends JpaRepository<News, Long>{  
	
	/*
	 * Spring Data JPA supports keywords 'first' or 'top' to limit the query results (e.g. findTopBy....).

	   An optional numeric value can be appended after 'top' or 'first' 
	   to limit the maximum number of results to be returned (e.g. findTop3By....). 
	   If this number is not used then only one entity is returned.
	   There's no difference between the keywords 'first' and 'top'.
	   
	 */
	List<News> findFirst6ByOrderByCreatedDateTimeDesc();
}
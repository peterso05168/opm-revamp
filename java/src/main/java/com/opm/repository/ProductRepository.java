package com.opm.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opm.model.Product;

 
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>{  
	List<Product> findByServiceId(long id);
}
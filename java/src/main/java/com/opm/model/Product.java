package com.opm.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = false, columnDefinition = "TEXT(10000)")
	private String description;
	
	@Column(name = "feature", nullable = false, columnDefinition = "TEXT(10000)")
	private String feature;
	
	@Column(name = "benefit", nullable = false, columnDefinition = "TEXT(10000)")
	private String benefit;
	
	@Transient
	private MultipartFile imgFile;
	
	@Column(name = "img", columnDefinition = "MEDIUMBLOB")
	private byte[] img;	
	
	@Column(name = "created_date_time", nullable = false)
	private Date createdDateTime;
	
	@Column(name = "expiry_date", nullable = true)
	private Date expiryDate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;
	
	public void setOnlyNameAndId() {
		this.benefit = null;
		this.createdDateTime = null;
		this.description = null;
		this.expiryDate = null;
		this.feature = null;
		this.img = null;
		this.service = null;
	}
}

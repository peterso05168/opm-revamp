package com.opm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="image")
public class Image {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Transient
	private MultipartFile imgFile;
	
	@Column(name = "img", columnDefinition = "MEDIUMBLOB")
	private byte[] img;	
	
	@Column(name = "created_date_time", nullable = false)
	private Date createdDateTime;
	
	@Column(name = "expiry_date", nullable = true)
	private Date expiryDate;
}

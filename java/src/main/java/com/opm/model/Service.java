package com.opm.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="service")
public class Service {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = false, columnDefinition = "TEXT(10000)")
	private String description;
	
	@Transient
	private MultipartFile imgFile;
	
	@Column(name = "img", columnDefinition = "MEDIUMBLOB")
	private byte[] img;	
	
	@Column(name = "created_date_time", nullable = false)
	private Date createdDateTime;
	
	@Column(name = "expiry_date", nullable = true)
	private Date expiryDate;
	
	@Column(name = "child", nullable = false)
	private byte child;	
	
	@JsonIgnore
	@OneToMany(
        mappedBy = "service",
        cascade = CascadeType.PERSIST,
        fetch = FetchType.LAZY
    )
	private List<Product> products;
	
	public void setOnlyNameAndId() {
		this.createdDateTime = null;
		this.description = null;
		this.expiryDate = null;
		this.img = null;
		this.child = 0;
	}
}

package com.opm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job_vacancy")
public class JobVacancy {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "job_title", nullable = false)
	private String jobTitle;
	
	@Column(name = "responsibilities", nullable = false, columnDefinition = "TEXT(10000)")
	private String responsibilities;
	
	@Column(name = "requirements", nullable = false, columnDefinition = "TEXT(10000)")
	private String requirements;
	
	@Column(name = "seq", nullable = false)
	private long seq;	
	
	@Column(name = "created_date_time", nullable = false)
	private Date createdDateTime;
	
	@Column(name = "expiry_date", nullable = true)
	private Date expiryDate;
}

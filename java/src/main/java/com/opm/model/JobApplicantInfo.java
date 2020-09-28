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
@Table(name="job_applicant_info")
public class JobApplicantInfo {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Transient
	private MultipartFile resume;
	
	@Column(name = "resume_name", nullable = false)
	private String resumeName;
	
	@Column(name = "resume_byte", nullable = false, columnDefinition = "MEDIUMBLOB")
	private byte[] resumeByteArray;	
	
	@Column(name = "created_date_time", nullable = false)
	private Date createdDateTime;
	
	@Column(name = "expiry_date", nullable = true)
	private Date expiryDate;
	
	@Column(name = "job_id", nullable = false)
	private Long jobId;
}

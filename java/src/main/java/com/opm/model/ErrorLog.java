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
@Table(name="error_log")
public class ErrorLog {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "msg", nullable = false)
	private String msg;
	
	@Column(name = "created_date_time", nullable = false)
	private Date createdDateTime;
}

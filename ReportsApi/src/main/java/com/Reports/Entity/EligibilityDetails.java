package com.Reports.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ELIGIBILITY_DETAILS")
public class EligibilityDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ELIG_ID")
	private Integer eligId;
	@Column(name="NAME")
	private String name;
	@Column(name="MOBILE")
	private Long mobile;
	@Column(name="EMAIL")
	private String email;
	@Column(name="GENDER")
	private Character gender;
	@Column(name="SSN")
	private Long ssn;
	@Column(name="PLAN_NAME")
	private String planName;
	@Column(name="PLAN_STATUS")
	private String planStatus;

	@Column(name="PLAN_START_DATE")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate planStartDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
	
	
	@Column(name="CREATED_DATE",updatable=false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name="UPDATED_DATE",insertable=false)
	@UpdateTimestamp
	private LocalDate updatedDate;
}

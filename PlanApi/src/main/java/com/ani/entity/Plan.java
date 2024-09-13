package com.ani.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="PLAN")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PLAN_ID")
	private Integer planId;
	@Column(name="PLAN_NAME")
	private String planName;
	@Column(name="PLAN_START_DATE")
	private LocalDate startDate;
	@Column(name="PLAN_END_DATE")
	private LocalDate endDate;
	@Column(name="PLAN_CATEGORY_ID")
	private Integer planCategoryId;
	@Column(name="PLAN_ACTIVE_SWITCH")
	private String planActiveSwitch;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="CREATED_DATE",updatable=false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name="UPDATED_DATE",insertable=false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	

}

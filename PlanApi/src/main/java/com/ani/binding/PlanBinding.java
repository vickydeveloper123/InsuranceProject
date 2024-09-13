package com.ani.binding;

import java.time.LocalDate;

import com.ani.entity.Plan;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanBinding {
	
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer planCategoryId;
	private String createdBy;
	private String updatedBy;
	private String planActiveSwitch;

}

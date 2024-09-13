package com.ani.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponse {
	
	private String planName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String categoryName;
	private String planActiveSwitch;

}

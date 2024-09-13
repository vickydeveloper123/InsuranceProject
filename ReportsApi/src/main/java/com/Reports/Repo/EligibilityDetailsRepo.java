package com.Reports.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Reports.Entity.EligibilityDetails;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Integer> {
	
	@Query("select distinct(planName) from EligibilityDetails")
	List<String> findPlanNames();
	
	@Query("select distinct(planStatus) from EligibilityDetails")
	List<String> findPlanStatus();

}

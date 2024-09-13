package com.ani.service;

import java.util.List;
import java.util.Map;

import com.ani.entity.Plan;
import com.ani.entity.PlanCategory;

public interface PlanService {
	
	public Map<Integer,String> getPlanCategories();
	
	public PlanCategory saveCategory(PlanCategory planCateogory);
	
	public boolean savePlan(Plan plan);
	
	public List<Plan> getAllPlans();
	
	public Plan getPlanById(Integer planId);
	
	public boolean updatePlan(Integer planId,Plan plan);
	
	public boolean deletePlan(Integer planId);
	
	public boolean planStatusChange(Integer planId,String status);

}

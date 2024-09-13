package com.ani.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ani.entity.Plan;
import com.ani.entity.PlanCategory;
import com.ani.repository.PlanCategoryRepo;
import com.ani.repository.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	private PlanRepo planRepo;
	private PlanCategoryRepo planCategoryRepo;

	@Autowired
	public PlanServiceImpl(PlanRepo planRepo, PlanCategoryRepo planCategoryRepo) {
		super();
		this.planRepo = planRepo;
		this.planCategoryRepo = planCategoryRepo;
	}

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> all = planCategoryRepo.findAll();
		Map<Integer, String> map = new HashMap();

		all.forEach(category -> {
			map.put(category.getPlanCategoryId(), category.getCategoryName()); // Here we have been passed the value in
																				// the form of key value pair.
		});
		return map;

	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan plan2 = planRepo.save(plan);
		return plan2.getPlanId() != null;

	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> all = planRepo.findAll();
		return all;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> byId = planRepo.findById(planId);
		if (byId.isPresent()) {
			return byId.get();
		} else
			return null;

	}

	@Override
	public boolean updatePlan(Integer planId, Plan plan) {
		/*
		 * Plan plan2 = planRepo.findById(planId).get();
		 * BeanUtils.copyProperties(plan,plan2);
		 */
		Plan plan2=null;
	Optional<Plan> byId = planRepo.findById(planId);
	if(byId.isPresent()) {
		 plan2 = byId.get();
		plan2.setCreatedBy(plan.getCreatedBy());
		plan2.setPlanName(plan.getPlanName());
		plan2.setEndDate(plan.getEndDate());
		plan2.setPlanActiveSwitch(plan.getPlanActiveSwitch());
		plan2.setPlanCategoryId(plan.getPlanCategoryId());
		plan2.setStartDate(plan.getStartDate());
		plan2.setUpdatedBy(plan.getUpdatedBy());
		planRepo.save(plan2);
		return true;

		
	}
	else
	{
		return false;
	}
		

	}

	@Override
	public boolean deletePlan(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> byId = planRepo.findById(planId);
		if (byId.isPresent()) {
			Plan plan = byId.get();
			plan.setPlanActiveSwitch(status);
			planRepo.save(plan);
			return true;
		}
		return false;

	}

	@Override
	public PlanCategory saveCategory(PlanCategory planCateogory) {
		PlanCategory save = planCategoryRepo.save(planCateogory);
		return save;
	}

}

package com.ani.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ani.binding.PlanBinding;
import com.ani.binding.PlanResponse;
import com.ani.constants.AppConstants;
import com.ani.entity.Plan;
import com.ani.props.AppProperties;
import com.ani.service.PlanService;

@RestController

public class PlanController {

	private PlanService planService;
	private AppConstants appConstants;
	private AppProperties props;

	@Autowired
	public PlanController(PlanService planService, AppConstants appConstants, AppProperties props) {
		super();
		this.planService = planService;
		this.appConstants = appConstants;
		this.props = props;
	}

	@PostMapping("/savePlan")
	public ResponseEntity<String> insertPlan(@RequestBody PlanBinding planBinding) {
		Plan plan = new Plan();
		BeanUtils.copyProperties(planBinding, plan);
		boolean savePlan = planService.savePlan(plan);
		String responseMsg = AppConstants.EMPTY_STRING;
		if (savePlan) {
			responseMsg = props.getMessages().get(AppConstants.PLAN_SAVE_SUCC);
		} else
			responseMsg = props.getMessages().get(AppConstants.PLAN_SAVE_FAIL);

		return new ResponseEntity<>(responseMsg, HttpStatus.OK);

	}

	@GetMapping("/getAllCategories")
	public ResponseEntity<Map<Integer, String>> getCategories() {
		Map<Integer, String> planCategories = planService.getPlanCategories();
		return new ResponseEntity<>(planCategories, HttpStatus.OK);
	}

	@GetMapping("/getAllPlans")
	public ResponseEntity<List<PlanResponse>> getAllPlanDetails() {
		List<Plan> allPlans = planService.getAllPlans();
		List<PlanResponse> planResponses = new ArrayList<>();
		for (Plan plan : allPlans) {
			Integer planCategoryId = plan.getPlanCategoryId();
			Map<Integer, String> planCategories = planService.getPlanCategories();
			String categoryName = planCategories.get(planCategoryId);
			PlanResponse planResponse = new PlanResponse();
			BeanUtils.copyProperties(plan, planResponse);
			planResponse.setCategoryName(categoryName);
			planResponses.add(planResponse);
			;
		}
		return new ResponseEntity<>(planResponses, HttpStatus.OK);
	}

	@GetMapping("/getById/{planId}")
	public ResponseEntity<PlanResponse> getPlanById(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		Map<Integer, String> planCategories = planService.getPlanCategories();
		String categoryName = planCategories.get(planId);

		PlanResponse planResponse = new PlanResponse();
		BeanUtils.copyProperties(plan, planResponse);
		planResponse.setCategoryName(categoryName);
		return new ResponseEntity<>(planResponse, HttpStatus.OK);

	}

	@PutMapping("/update/{planId}")
	public ResponseEntity<String> updatePlan(@PathVariable Integer planId, @RequestBody PlanBinding planBinding) {
		Plan plan = new Plan();
		BeanUtils.copyProperties(planBinding, plan);
		boolean updatePlan = planService.updatePlan(planId, plan);
		String responseMsg = AppConstants.EMPTY_STRING;
		if (updatePlan) {
			responseMsg = props.getMessages().get(AppConstants.PLAN_UPDATE_SUCC);
		} else
			responseMsg = props.getMessages().get(AppConstants.PLAN_UPDATE_FAIL);

		return new ResponseEntity<>(responseMsg, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> delete(@PathVariable Integer planId) {
		boolean deletePlan = planService.deletePlan(planId);
		String responseMsg = AppConstants.EMPTY_STRING;
		if (deletePlan) {
			responseMsg = props.getMessages().get(AppConstants.PLAN_DELETE_SUCC);
		} else
			responseMsg = props.getMessages().get(AppConstants.PLAN_DELETE_FAIL);

		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

	@PutMapping("/updateStatus/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean planStatusChange = planService.planStatusChange(planId, status);

		String responseMsg = AppConstants.EMPTY_STRING;
		if (planStatusChange) {
			responseMsg = props.getMessages().get(AppConstants.PLAN_STATUS_CHANGE);
		} else
			responseMsg = props.getMessages().get(AppConstants.PLAN_STATUS_CHANGE_FAIL);

		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

}

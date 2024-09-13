package com.ani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ani.entity.PlanCategory;
@Repository
public interface PlanCategoryRepo extends JpaRepository<PlanCategory,Integer>{

}

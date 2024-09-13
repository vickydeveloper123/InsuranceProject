package com.ani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ani.entity.Plan;
@Repository
public interface PlanRepo extends JpaRepository<Plan,Integer>{

}

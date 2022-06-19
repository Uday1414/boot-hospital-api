package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.hospitalapi.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}

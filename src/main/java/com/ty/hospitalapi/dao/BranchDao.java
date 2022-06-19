package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.repository.BranchRepository;

@Repository
public class BranchDao {
	
	@Autowired
	BranchRepository branchRepository;

	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	public List<Branch> getAllBranchs() {
		return branchRepository.findAll();
	}

	public Branch getBranchById(int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Branch updateBranchById(Branch branch, int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isPresent()) {
			Branch b = optional.get();
			branch.setHospital(b.getHospital());
			return branchRepository.save(branch);
		} else {
			return null;
		}
	}

	public boolean deleteBranchById(int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isPresent()) {
			branchRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}

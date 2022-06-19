package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.BranchDao;
import com.ty.hospitalapi.dao.HospitalDao;
import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class BranchService {
	
	@Autowired
	BranchDao branchDao;
	
	@Autowired
	HospitalDao hospitalDao;
	
	public ResponseStructure<Branch> saveBranch(Branch branch , int id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		Hospital hospital = hospitalDao.getHospitalById(id);
		branch.setHospital(hospital);
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(branchDao.saveBranch(branch));
		return responseStructure;
	}
	
	public ResponseStructure<List<Branch>> getAllBranchs(){
		List<Branch> branchs = branchDao.getAllBranchs();
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<>();
		if(branchs.size()>0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(branchs);
		}else {
			throw new NoIdFoundException("No branchs added");
		}
		return responseStructure;
	}
	
	public ResponseStructure<Branch> getBranchById( int id){
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		Branch branch = branchDao.getBranchById(id);
		if(branch!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(branch);
		}else {
			throw new NoIdFoundException("branch id "+id+" Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<Branch> updateBranchById( Branch branch ,  int id){
		Branch branch1 = branchDao.updateBranchById(branch, id);
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		if(branch1!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(branch1);
		}else {
			throw new NoIdFoundException("branch id "+id+" Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<String> deleteBranchById( int id){
		boolean flag = branchDao.deleteBranchById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if(flag) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Data Deleted");
		}else {
			throw new NoIdFoundException("branch id "+id+" Does not exist");
		}
		return responseStructure;
	}
	
}

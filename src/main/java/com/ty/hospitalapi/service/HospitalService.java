package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.HospitalDao;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class HospitalService {

	@Autowired
	HospitalDao hospitalDao;

	public ResponseStructure<Hospital> saveHospital(Hospital hospital) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(hospitalDao.saveHospital(hospital));
		return responseStructure;
	}

	public ResponseStructure<List<Hospital>> getAllHospitals() {
		List<Hospital> hospitals = hospitalDao.getAllHospitals();
		ResponseStructure<List<Hospital>> responseStructure = new ResponseStructure<>();
		if (hospitals.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(hospitals);
		} else {
			throw new NoIdFoundException("No hospitals added");
		}
		return responseStructure;
	}

	public ResponseStructure<Hospital> getHospitalById(int id) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();
		Hospital hospital = hospitalDao.getHospitalById(id);
		if (hospital != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(hospital);
		} else {
			throw new NoIdFoundException("hospital id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<Hospital> updateHospitalById(Hospital hospital, int id) {
		Hospital hospital1 = hospitalDao.updateHospitalById(hospital, id);
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();
		if (hospital1 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(hospital1);
		} else {
			throw new NoIdFoundException("hospital id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteHospitalById(int id) {
		boolean flag = hospitalDao.deleteHospitalById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (flag) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Data Deleted");
		} else {
			throw new NoIdFoundException("hospital id " + id + " Does not exist");
		}
		return responseStructure;
	}

}

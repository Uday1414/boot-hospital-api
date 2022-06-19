package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.repository.HospitalRepository;

@Repository
public class HospitalDao {
	
	@Autowired
	HospitalRepository hospitalRepository;

	public Hospital saveHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}

	public List<Hospital> getAllHospitals() {
		return hospitalRepository.findAll();
	}

	public Hospital getHospitalById(int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Hospital updateHospitalById(Hospital hospital, int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if (optional.isPresent()) {
			return hospitalRepository.save(hospital);
		} else {
			return null;
		}
	}

	public boolean deleteHospitalById(int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if (optional.isPresent()) {
			hospitalRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}

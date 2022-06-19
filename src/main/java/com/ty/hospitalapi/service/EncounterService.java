package com.ty.hospitalapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.BranchDao;
import com.ty.hospitalapi.dao.EncounterDao;
import com.ty.hospitalapi.dao.PersonDao;
import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class EncounterService {

	@Autowired
	EncounterDao encounterDao;
	@Autowired
	PersonDao personDao;
	@Autowired
	BranchDao branchDao;

	public ResponseStructure<Encounter> saveEncounter(Encounter encounter , int pid , int bid) {
		ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
		Person person = personDao.getPersonById(pid);
		Branch branch = branchDao.getBranchById(bid);
		encounter.setBranch(branch);
		encounter.setPerson(person);
		encounter.setAdmitDateAndTime(LocalDateTime.now());
		encounter.setDischargeDateAndTime(LocalDateTime.now());
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(encounterDao.saveEncounter(encounter));
		return responseStructure;
	}

	public ResponseStructure<List<Encounter>> getAllEncounters() {
		List<Encounter> encounters = encounterDao.getAllEncounters();
		ResponseStructure<List<Encounter>> responseStructure = new ResponseStructure<>();
		if (encounters.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(encounters);
		} else {
			throw new NoIdFoundException("No encounters added");
		}
		return responseStructure;
	}

	public ResponseStructure<Encounter> getEncounterById(int id) {
		ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
		Encounter encounter = encounterDao.getEncounterById(id);
		if (encounter != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(encounter);
		} else {
			throw new NoIdFoundException("encounter id "+id+" Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<Encounter> updateEncounterById(Encounter encounter, int id) {
		Encounter encounter1 = encounterDao.updateEncounterById(encounter, id);
		ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
		if (encounter1 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(encounter1);
		} else {
			throw new NoIdFoundException("encounter id "+id+" Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteEncounterById(int id) {
		boolean flag = encounterDao.deleteEncounterById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (flag) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Data Deleted");
		} else {
			throw new NoIdFoundException("encounter id "+id+" Does not exist");
		}
		return responseStructure;
	}

}

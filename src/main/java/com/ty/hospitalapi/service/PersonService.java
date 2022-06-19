package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.PersonDao;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;
import com.ty.hospitalapi.dto.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonDao personDao;
	
	public ResponseStructure<Person> savePerson(Person person) {
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(personDao.savePerson(person));
		return responseStructure;
	}
	
	public ResponseStructure<List<Person>> getAllPersons(){
		List<Person> persons = personDao.getAllPersons();
		ResponseStructure<List<Person>> responseStructure = new ResponseStructure<>();
		if(persons.size()>0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(persons);
		}else {
			throw new NoIdFoundException("No Persons found");
		}
		return responseStructure;
	}
	
	public ResponseStructure<Person> getPersonById( int id){
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		Person person = personDao.getPersonById(id);
		if(person!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(person);
		}else {
			throw new NoIdFoundException("person id " + id + " Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<Person> updatePersonById( Person person ,  int id){
		Person person1 = personDao.updatePersonById(person, id);
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		if(person1!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(person1);
		}else {
			throw new NoIdFoundException("person id " + id + " Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<String> deletePersonById( int id){
		boolean flag = personDao.deletePersonById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if(flag) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Data Deleted");
		}else {
			throw new NoIdFoundException("Person id " + id + " Does not exist");
		}
		return responseStructure;
	}
	
	
}

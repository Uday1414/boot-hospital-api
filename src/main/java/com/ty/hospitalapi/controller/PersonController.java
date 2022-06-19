package com.ty.hospitalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping("/persons")
	public ResponseStructure<Person> savePerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
	
	@GetMapping("/persons")
	public ResponseStructure<List<Person>> getAllPersons(){
		return personService.getAllPersons();
	}
	
	@GetMapping("/persons/{id}")
	public ResponseStructure<Person> getPersonById(@PathVariable int id){
		return personService.getPersonById(id);
	}
	
	@PutMapping("/persons/{id}")
	public ResponseStructure<Person> updatePersonById(@RequestBody Person person , @PathVariable int id){
		return personService.updatePersonById(person ,id);
	}
	
	@DeleteMapping("/persons/{id}")
	public ResponseStructure<String> deletePersonById(@PathVariable int id){
		return personService.deletePersonById(id);
	}
	
}

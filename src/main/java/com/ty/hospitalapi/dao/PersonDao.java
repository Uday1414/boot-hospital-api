package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.repository.PersonRepository;

@Repository
public class PersonDao {

	@Autowired
	PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Person getPersonById(int id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Person updatePersonById(Person person, int id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isPresent()) {
			return personRepository.save(person);
		} else {
			return null;
		}
	}

	public boolean deletePersonById(int id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isPresent()) {
			personRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}

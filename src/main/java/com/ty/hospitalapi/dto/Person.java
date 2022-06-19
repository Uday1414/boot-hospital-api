package com.ty.hospitalapi.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int person_id;
	private String person_name;
	private long person_phone;
	private String person_email;
	private int person_age;
	@JsonIgnore
	@OneToMany(mappedBy = "person")
	private List<Encounter> encounters;

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public long getPerson_phone() {
		return person_phone;
	}

	public void setPerson_phone(long person_phone) {
		this.person_phone = person_phone;
	}

	public String getPerson_email() {
		return person_email;
	}

	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}

	public int getPerson_age() {
		return person_age;
	}

	public void setPerson_age(int person_age) {
		this.person_age = person_age;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

}

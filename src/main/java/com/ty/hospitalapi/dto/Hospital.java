package com.ty.hospitalapi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity//this entity creates hospital table in database.
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospital_id;
	private String hospital_name;
	private String hospital_website;
	private String hospital_gstnumber;
	
	@OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
	private List<Branch> branchs;

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getHospital_website() {
		return hospital_website;
	}

	public void setHospital_website(String hospital_website) {
		this.hospital_website = hospital_website;
	}

	public String getHospital_gstnumber() {
		return hospital_gstnumber;
	}

	public void setHospital_gstnumber(String hospital_gstnumber) {
		this.hospital_gstnumber = hospital_gstnumber;
	}

	public List<Branch> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}

}

package com.ty.hospitalapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int encounter_id;
	private LocalDateTime admitDateAndTime ;
	private LocalDateTime dischargeDateAndTime;
	private String reason;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Branch branch;
	
	@OneToMany(mappedBy = "encounter")
	private List<MedOrder> medOrders;
	@ManyToOne
	@JoinColumn
	private Person person;
	
	public int getEncounter_id() {
		return encounter_id;
	}
	public void setEncounter_id(int encounter_id) {
		this.encounter_id = encounter_id;
	}
	public LocalDateTime getAdmitDateAndTime() {
		return admitDateAndTime;
	}
	public void setAdmitDateAndTime(LocalDateTime admitDateAndTime) {
		this.admitDateAndTime = admitDateAndTime;
	}
	public LocalDateTime getDischargeDateAndTime() {
		return dischargeDateAndTime;
	}
	public void setDischargeDateAndTime(LocalDateTime dischargeDateAndTime) {
		this.dischargeDateAndTime = dischargeDateAndTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public List<MedOrder> getMedOrders() {
		return medOrders;
	}
	public void setMedOrders(List<MedOrder> medOrders) {
		this.medOrders = medOrders;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}

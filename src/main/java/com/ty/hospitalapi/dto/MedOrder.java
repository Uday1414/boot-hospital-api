package com.ty.hospitalapi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MedOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medOrder_id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Encounter encounter;
	
	@OneToMany(mappedBy = "medOrder" , cascade =  CascadeType.ALL)
	private List<Item> items;
	private double total;

	public int getMedOrder_id() {
		return medOrder_id;
	}

	public void setMedOrder_id(int medOrder_id) {
		this.medOrder_id = medOrder_id;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}

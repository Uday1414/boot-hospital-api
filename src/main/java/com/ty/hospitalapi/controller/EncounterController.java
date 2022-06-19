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

import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.service.EncounterService;

@RestController
public class EncounterController {
	
	@Autowired
	EncounterService encounterService;
	
	@PostMapping("/encounters/{pid}/{bid}")
	public ResponseStructure<Encounter> saveEncounter(@RequestBody Encounter encounter , @PathVariable int pid , @PathVariable int bid) {
		return encounterService.saveEncounter(encounter,pid,bid);
	}
	
	@GetMapping("/encounters")
	public ResponseStructure<List<Encounter>> getAllEncounters(){
		return encounterService.getAllEncounters();
	}
	
	@GetMapping("/encounters/{id}")
	public ResponseStructure<Encounter> getEncounterById(@PathVariable int id){
		return encounterService.getEncounterById(id);
	}
	
	@PutMapping("/encounters/{id}")
	public ResponseStructure<Encounter> updateEncounterById(@RequestBody Encounter encounter , @PathVariable int id){
		return encounterService.updateEncounterById(encounter ,id);
	}
	
	@DeleteMapping("/encounters/{id}")
	public ResponseStructure<String> deleteEncounterById(@PathVariable int id){
		return encounterService.deleteEncounterById(id);
	}
}

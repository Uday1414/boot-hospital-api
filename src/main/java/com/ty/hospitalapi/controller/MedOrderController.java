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

import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.service.MedOrderService;

@RestController
public class MedOrderController {
	@Autowired
	MedOrderService medOrderService;
	
	@PostMapping("/medOrders/{eid}")
	public ResponseStructure<MedOrder> saveMedOrder(@RequestBody MedOrder medOrder , @PathVariable int eid) {
		return medOrderService.saveMedOrder(medOrder,eid);
	}
	
	@GetMapping("/medOrders")
	public ResponseStructure<List<MedOrder>> getAllMedOrders(){
		return medOrderService.getAllMedOrders();
	}
	
	@GetMapping("/medOrders/{id}")
	public ResponseStructure<MedOrder> getMedOrderById(@PathVariable int id){
		return medOrderService.getMedOrderById(id);
	}
	
	@PutMapping("/medOrders/{id}")
	public ResponseStructure<MedOrder> updateMedOrderById(@RequestBody MedOrder medOrder , @PathVariable int id){
		return medOrderService.updateMedOrderById(medOrder ,id);
	}
	
	@DeleteMapping("/medOrders/{id}")
	public ResponseStructure<String> deleteMedOrderById(@PathVariable int id){
		return medOrderService.deleteMedOrderById(id);
	}
}

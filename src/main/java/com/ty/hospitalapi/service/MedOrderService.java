package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.EncounterDao;
import com.ty.hospitalapi.dao.MedOrderDao;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Item;
import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class MedOrderService {
	@Autowired
	MedOrderDao medOrderDao;
	@Autowired
	EncounterDao encounterDao ;

	public ResponseStructure<MedOrder> saveMedOrder(MedOrder medOrder , int id) {
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		Encounter encounter = encounterDao.getEncounterById(id);
		medOrder.setEncounter(encounter);
		double total=0;
		for (Item item : medOrder.getItems()) {
			item.setMedOrder(medOrder);
			total+=(item.getItem_cost()*item.getItem_quantity());
		}
		medOrder.setTotal(total);
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(medOrderDao.saveMedOrder(medOrder));
		return responseStructure;
	}

	public ResponseStructure<List<MedOrder>> getAllMedOrders() {
		List<MedOrder> medOrders = medOrderDao.getAllMedOrders();
		ResponseStructure<List<MedOrder>> responseStructure = new ResponseStructure<>();
		if (medOrders.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(medOrders);
		} else {
			throw new NoIdFoundException("No medOrders added");
		}
		return responseStructure;
	}

	public ResponseStructure<MedOrder> getMedOrderById(int id) {
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		MedOrder medOrder = medOrderDao.getMedOrderById(id);
		if (medOrder != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(medOrder);
		} else {
			throw new NoIdFoundException("medOrder id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<MedOrder> updateMedOrderById(MedOrder medOrder, int id) {
		MedOrder medOrder1 = medOrderDao.updateMedOrderById(medOrder, id);
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		if (medOrder1 != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(medOrder1);
		} else {
			throw new NoIdFoundException("medOrder id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteMedOrderById(int id) {
		boolean flag = medOrderDao.deleteMedOrderById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (flag) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Data Deleted");
		} else {
			throw new NoIdFoundException("medOrder id " + id + " Does not exist");
		}
		return responseStructure;
	}

}

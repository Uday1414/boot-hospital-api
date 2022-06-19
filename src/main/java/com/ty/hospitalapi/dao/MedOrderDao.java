package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Item;
import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.repository.MedOrderRepository;
@Repository
public class MedOrderDao {
	
	@Autowired
	MedOrderRepository medOrderRepository;

	public MedOrder saveMedOrder(MedOrder medOrder) {
		return medOrderRepository.save(medOrder);
	}

	public List<MedOrder> getAllMedOrders() {
		return medOrderRepository.findAll();
	}

	public MedOrder getMedOrderById(int id) {
		Optional<MedOrder> optional = medOrderRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public MedOrder updateMedOrderById(MedOrder medOrder, int id) {
		Optional<MedOrder> optional = medOrderRepository.findById(id);
		if (optional.isPresent()) {
			MedOrder m = optional.get();
			medOrder.setEncounter(m.getEncounter());
			double total=m.getTotal();
			for (Item item : medOrder.getItems()) {
				item.setMedOrder(medOrder);
				total+=(item.getItem_cost()*item.getItem_quantity());
			}
			medOrder.setTotal(total);
			return medOrderRepository.save(medOrder);
		} else {
			return null;
		}
	}

	public boolean deleteMedOrderById(int id) {
		Optional<MedOrder> optional = medOrderRepository.findById(id);
		if (optional.isPresent()) {
			medOrderRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

}

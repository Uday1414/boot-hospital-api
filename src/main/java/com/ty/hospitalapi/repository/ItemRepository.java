package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.hospitalapi.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}

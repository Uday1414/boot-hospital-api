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

import com.ty.hospitalapi.dto.Login;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users/{bid}")
	public ResponseStructure<User> saveUser(@RequestBody User user , @PathVariable int bid) {
		return userService.saveUser(user,bid);
	}
	
	@GetMapping("/users")
	public ResponseStructure<List<User>> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public ResponseStructure<User> getUserById(@PathVariable int id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/users/{id}")
	public ResponseStructure<User> updateUserById(@RequestBody User user , @PathVariable int id){
		return userService.updateUserById(user ,id);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseStructure<String> deleteUserById(@PathVariable int id){
		return userService.deleteUserById(id);
	}
	
	@PostMapping("/users/login")
	public ResponseStructure<User> validateUser(@RequestBody Login login) {
		return userService.validateUser(login);
	}
}

package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.BranchDao;
import com.ty.hospitalapi.dao.UserDao;
import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Login;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.exception.InvalidCredentialsException;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	BranchDao branchDao;
	
	public ResponseStructure<User> saveUser(User user , int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		Branch branch = branchDao.getBranchById(id);
		user.setBranch(branch);
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(userDao.saveUser(user));
		return responseStructure;
	}
	
	public ResponseStructure<List<User>> getAllUsers(){
		List<User> users = userDao.getAllUsers();
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		if(users.size()>0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(users);
		}else {
			throw new NoIdFoundException("No Users added");
		}
		return responseStructure;
	}
	
	public ResponseStructure<User> getUserById( int id){
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = userDao.getUserById(id);
		if(user!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
		}else {
			throw new NoIdFoundException("User id "+id+" Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<User> updateUserById( User user ,  int id){
		User user1 = userDao.updateUserById(user, id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if(user1!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Updated");
			responseStructure.setData(user1);
		}else {
			throw new NoIdFoundException("User id "+id+" Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<String> deleteUserById( int id){
		boolean flag = userDao.deleteUserById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if(flag) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("Data Deleted");
		}else {
			throw new NoIdFoundException("User id "+id+" Does not exist");
		}
		return responseStructure;
	}
	
	public ResponseStructure<User> validateUser(Login login) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = userDao.validateUser(login);
		if(user!=null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Login Success");
			responseStructure.setData(user);
		}else {
			throw new InvalidCredentialsException();
		}
		return responseStructure;
	}
}

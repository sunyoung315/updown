package com.ssafy.updown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.updown.model.dto.User;
import com.ssafy.updown.model.service.UserService;

@RestController
@RequestMapping("/updown")
public class UserController {

	@Autowired
	private UserService uService;
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> uList = uService.getAllUsers();
		return new ResponseEntity<List<User>>(uList, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getOneUser(@PathVariable String id) {
		User user = uService.getOneUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/user/signup")
	public ResponseEntity<Void> signUp(User user) {
		uService.signUp(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/user/modify")
	public ResponseEntity<Void> modifyUser(User user) {
		uService.modifyUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/user/unregister")
	public ResponseEntity<Void> removeUser(String id) {
		uService.removeUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

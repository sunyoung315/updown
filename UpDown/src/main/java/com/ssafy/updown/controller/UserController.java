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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.updown.model.dto.User;
import com.ssafy.updown.model.service.UserService;

@RestController
@RequestMapping("/updown")
public class UserController {

	@Autowired
	private UserService uService;
	
	// 전체 사용자 가져오기
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> uList = uService.getAllUsers();
		return new ResponseEntity<List<User>>(uList, HttpStatus.OK);
	}
	
//	// 계정 공개를 희망하는 사용자만 가져오기
//	@GetMapping("/user/open")
//	public ResponseEntity<List<User>> getNoSecretUsers() {
//		List<User> uList = uService.getNoSecretUsers();
//		return new ResponseEntity<List<User>>(uList, HttpStatus.OK);
//	}
	
	@PostMapping("user/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		User matchedUser = uService.getOneUser(user.getId(), user.getPassword());
		if(matchedUser == null)
			return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<User>(matchedUser, HttpStatus.OK);
	}
	
	@PostMapping("/user/signup")
	public ResponseEntity<Void> signUp(@RequestBody User user) {
		System.out.println(user);
		uService.signUp(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/user/modify")
	public ResponseEntity<Void> modifyUser(@RequestBody User user) {
		uService.modifyUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/user/unregister")
	public ResponseEntity<Void> removeUser(String id) {
		uService.removeUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

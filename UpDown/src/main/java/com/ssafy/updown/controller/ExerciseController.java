package com.ssafy.updown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.updown.model.dto.Exercise;
import com.ssafy.updown.model.service.ExerciseService;

@RestController
@RequestMapping("/updown")
public class ExerciseController {
	
	@Autowired
	private ExerciseService eService;
	
	@PostMapping("/exercise")
	public ResponseEntity<Void> upload(Exercise exercise) {
		eService.upload(exercise);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/exercise")
	public ResponseEntity<List<Exercise>> getList(String loginUserId) {
		List<Exercise> eList = eService.getList(loginUserId);
		return new ResponseEntity<List<Exercise>>(eList, HttpStatus.OK);
	}
	
	@GetMapping("/exercise/{no}")
	public ResponseEntity<Exercise> getOne(int exerciseNo) {
		Exercise exercise = eService.getOne(exerciseNo);
		return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
	}
	
	@PutMapping("/exercise")
	public ResponseEntity<Void> modify(Exercise exercise) {
		eService.modify(exercise);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/exercise")
	public ResponseEntity<Void> remove(int exerciseNo) {
		eService.remove(exerciseNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}

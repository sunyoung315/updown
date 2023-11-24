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

import com.ssafy.updown.model.dto.Exercise;
import com.ssafy.updown.model.dto.ExerciseInfo;
import com.ssafy.updown.model.service.ExerciseService;

@RestController
@RequestMapping("/updown")
public class ExerciseController {
	
	@Autowired
	private ExerciseService eService;
	
	@PostMapping("/exercise/upload")
	public ResponseEntity<Void> uploadExercise(@RequestBody Exercise exercise) {
		eService.uploadExercise(exercise);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/exercise")
	public ResponseEntity<List<Exercise>> getExerciseList(String loginUserId, String regDate) {
		List<Exercise> eList = eService.getExerciseList(loginUserId, regDate);
		return new ResponseEntity<List<Exercise>>(eList, HttpStatus.OK);
	}
	
	@GetMapping("/exercise/{no}")
	public ResponseEntity<Exercise> getOneExercise(@PathVariable int no) {
		Exercise exercise = eService.getOneExercise(no);
		return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
	}
	
	@PutMapping("/exercise/modify")
	public ResponseEntity<Void> modifyExercise(@RequestBody Exercise exercise) {
		eService.modifyExercise(exercise);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/exercise/remove/{no}")
	public ResponseEntity<Void> removeExercise(@PathVariable int no) {
		eService.removeExercise(no);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/exercise/search/{word}")
	public ResponseEntity<List<ExerciseInfo>> getExerciseInfoList(@PathVariable String word) {
		List<ExerciseInfo> eiList = eService.getExerciseInfoList(word);
		return new ResponseEntity<List<ExerciseInfo>>(eiList, HttpStatus.OK);
	}
	
	@GetMapping("/exercise/searchDetail/{type}")
	public ResponseEntity<ExerciseInfo> getExerciseInfo(@PathVariable String type) {
		ExerciseInfo eInfo = eService.getExerciseInfo(type);
		return new ResponseEntity<ExerciseInfo>(eInfo, HttpStatus.OK);
	}
	
}

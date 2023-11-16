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

import com.ssafy.updown.model.dto.Diet;
import com.ssafy.updown.model.service.DietService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/updown/diet")
public class DietController {

	@Autowired
	DietService dietService;

	// 식단 등록
	@PostMapping("/")
	public ResponseEntity<Void> uploadDiet(@RequestBody Diet diet) {
		dietService.uploadDiet(diet);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 식단 수정
	@PutMapping("/")
	public ResponseEntity<?> modifyDiet(@RequestBody Diet diet) {
		// 만약 식단이 잘 수정되었다면
		if (dietService.modifyDiet(diet)) {
			return new ResponseEntity<Diet>(diet, HttpStatus.OK);
		}
		// 식단이 잘 수정되지 않았다면
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 식단 삭제
	@DeleteMapping("/")
	public ResponseEntity<Void> removeDiet(int no){
		dietService.removeDiet(no);
		return new ResponseEntity<Void>(HttpStatus.OK);		
	}

	// 하루 식단 기록 조회
	@GetMapping("/list/{regDate}")
	public ResponseEntity<List<Diet>> getDietOneday(@PathVariable String regDate){
		List<Diet> dietList = dietService.getDietOneday(regDate);
		return new ResponseEntity<List<Diet>>(dietList, HttpStatus.OK);		
	}

	// 식단 하나 조회
	@GetMapping("/{no}")
	public ResponseEntity<Diet> getDietOne(@PathVariable int no){
		Diet diet = dietService.getDietOne(no);
		return new ResponseEntity<Diet>(diet, HttpStatus.OK);
	}

}

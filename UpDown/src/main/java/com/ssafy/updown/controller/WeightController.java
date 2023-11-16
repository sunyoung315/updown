package com.ssafy.updown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.updown.model.dto.Weight;
import com.ssafy.updown.model.service.WeightService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/updown/weight")
public class WeightController {

	@Autowired
	private WeightService weightService;

	// 체중 등록
	@PostMapping("/")
	public ResponseEntity<Void> uploadWeight(@RequestBody Weight weight) {
		weightService.uploadWeight(weight);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 체중 수정
	@PutMapping("/")
	public ResponseEntity<?> modifyWeight(@RequestBody Weight weight) {
		// 데이터가 잘 수정되었다면
		if (weightService.modifyWeight(weight))
			return new ResponseEntity<Weight>(weight, HttpStatus.OK);
		// 데이터가 잘 수정되지 않았다면
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 당일 체중 조회
	@GetMapping("/")
	public ResponseEntity<Weight> detailWeight(){
		Weight weight =  weightService.getWeight();
		return new ResponseEntity<Weight>(weight, HttpStatus.OK);		
	}
	

}

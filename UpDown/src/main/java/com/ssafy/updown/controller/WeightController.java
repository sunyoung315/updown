package com.ssafy.updown.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.updown.model.dto.Weight;
import com.ssafy.updown.model.service.WeightService;


@RestController
@RequestMapping("/updown")
public class WeightController {

	@Autowired
	private WeightService weightService;

	// 체중 등록
	@PostMapping("/weight/upload")
	public ResponseEntity<Void> uploadWeight(@RequestBody Weight weight) {
		weightService.uploadWeight(weight);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 체중 수정
	@PutMapping("/weight/modify")
	public ResponseEntity<?> modifyWeight(@RequestBody Weight weight) {
		if (weightService.modifyWeight(weight))
			return new ResponseEntity<Weight>(weight, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 하루 체중 기록 조회
	@GetMapping("/weight")
	public ResponseEntity<?> detailWeight(@Param("loginUserId") String loginUserId, @Param("regDate") String regDate) {
		Weight weight =  weightService.getWeight(loginUserId, regDate);
		return new ResponseEntity<Weight>(weight, HttpStatus.OK);		
	}
	
	//7일간의 체중 기록 초죄
	@GetMapping("/weight/list")
	public ResponseEntity<List<Weight>> getWeightList(String loginUserId) {
		List<Weight> wList = weightService.getWeightList(loginUserId);
		return new ResponseEntity<List<Weight>>(wList, HttpStatus.OK);
	}
	

}

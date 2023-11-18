package com.ssafy.updown.controller;

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
		System.out.println(weight);
		weightService.uploadWeight(weight);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 체중 수정
	@PutMapping("/weight/modify")
	public ResponseEntity<?> modifyWeight(@RequestBody Weight weight) {
		// 데이터가 잘 수정되었다면
		if (weightService.modifyWeight(weight))
			return new ResponseEntity<Weight>(weight, HttpStatus.OK);
		// 데이터가 잘 수정되지 않았다면
		System.out.println("수정 안 됨");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 하루 체중 기록 조회
	@GetMapping("/weight")
	public ResponseEntity<?> detailWeight(@Param("loginUserId") String loginUserId, @Param("regDate") String regDate){
		Weight weight =  weightService.getWeight(loginUserId, regDate);
		return new ResponseEntity<Weight>(weight, HttpStatus.OK);		
	}
	

}

package com.ssafy.updown.model.dao;

import com.ssafy.updown.model.dto.Weight;

public interface WeightDao {

	// 체중 등록
	void upload(Weight weight);

	// 체중 수정
	int modify(Weight weight);

	// 당일 체중 조회
	Weight select();
	
}

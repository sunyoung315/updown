package com.ssafy.updown.model.dao;

import com.ssafy.updown.model.dto.Weight;

public interface WeightDao {

	// 체중 등록
	void insertWeight(Weight weight);

	// 체중 수정
	int updateWeight(Weight weight);

	// 당일 체중 조회
	Weight selectWeight(String loginUserId, String regDate);
	
}

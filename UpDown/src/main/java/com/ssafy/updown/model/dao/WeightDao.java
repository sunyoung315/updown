package com.ssafy.updown.model.dao;

import java.util.List;

import com.ssafy.updown.model.dto.Weight;

public interface WeightDao {

	// 체중 등록
	void insertWeight(Weight weight);

	// 체중 수정
	int updateWeight(Weight weight);

	// 당일 체중 조회
	Weight selectWeight(String loginUserId, String regDate);
	
	//7일간의 체중 기록 초죄
	List<Weight> selectWeightList(String loginUserId);
	
}

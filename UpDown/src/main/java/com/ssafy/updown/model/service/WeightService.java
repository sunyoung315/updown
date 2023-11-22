package com.ssafy.updown.model.service;

import java.util.List;

import com.ssafy.updown.model.dto.Weight;

public interface WeightService {

	void uploadWeight(Weight weight);

	boolean modifyWeight(Weight weight);

	Weight getWeight(String loginUserId, String regDate);
	
	List<Weight> getWeightList(String loginUserId);

}

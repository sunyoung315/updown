package com.ssafy.updown.model.service;

import com.ssafy.updown.model.dto.Weight;

public interface WeightService {

	void uploadWeight(Weight weight);

	boolean modifyWeight(Weight weight);

	Weight getWeight();

}

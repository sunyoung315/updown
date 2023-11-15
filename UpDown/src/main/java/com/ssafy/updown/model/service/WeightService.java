package com.ssafy.updown.model.service;

import com.ssafy.updown.model.dto.Weight;

public interface WeightService {

	void upload(Weight weight);

	boolean modify(Weight weight);

	Weight getWeight();

}

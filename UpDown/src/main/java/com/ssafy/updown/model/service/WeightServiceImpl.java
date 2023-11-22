package com.ssafy.updown.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.updown.model.dao.WeightDao;
import com.ssafy.updown.model.dto.Weight;

@Service
public class WeightServiceImpl implements WeightService {

	@Autowired
	WeightDao weightDao;

	// 체중 등록
	@Override
	public void uploadWeight(Weight weight) {
		weightDao.insertWeight(weight);
	}

	// 체중 수정
	@Override
	public boolean modifyWeight(Weight weight) {
		// 잘 수정 되었다면 true 반환
		return weightDao.updateWeight(weight) > 0;
	}

	// 당일 체중 조회
	@Override
	public Weight getWeight(String loginUserId, String regDate) {
		Weight weight = weightDao.selectWeight(loginUserId, regDate);
		return weight;
	}

	//7일간의 체중 기록 초죄
	@Override
	public List<Weight> getWeightList(String loginUserId) {
		List<Weight> wList = weightDao.selectWeightList(loginUserId);
		return wList;
	}

}

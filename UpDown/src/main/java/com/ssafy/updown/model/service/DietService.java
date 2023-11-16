package com.ssafy.updown.model.service;

import java.util.List;

import com.ssafy.updown.model.dto.Diet;

public interface DietService {

	// 식단 등록
	void uploadDiet(Diet diet);

	// 식단 수정
	boolean modifyDiet(Diet diet);

	// 식단 삭제
	void removeDiet(int no);

	// 하루 식단 기록 조회
	List<Diet> getDietOneday(String regDate);

	// 식단 하나 조회
	Diet getDietOne(int no);

}

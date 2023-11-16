package com.ssafy.updown.model.dao;

import java.util.List;

import com.ssafy.updown.model.dto.Diet;

public interface DietDao {

	// 식단 등록
	void insertDiet(Diet diet);

	// 식단 수정
	int updateDiet(Diet diet);

	// 식단 삭제
	void deleteDiet(int no);

	// 하루 식단 기록 조회
	List<Diet> getDietOneday(String loginUserId, String regDate);

	// 식단 하나 조회
	Diet getDietOne(int no);

}

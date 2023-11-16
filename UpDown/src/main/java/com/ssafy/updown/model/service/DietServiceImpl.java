package com.ssafy.updown.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.updown.model.dao.DietDao;
import com.ssafy.updown.model.dto.Diet;

@Service
public class DietServiceImpl implements DietService {

	@Autowired
	DietDao dietDao;

	// 식단 등록
	@Override
	public void uploadDiet(Diet diet) {
		dietDao.insertDiet(diet);
	}

	// 식단 수정
	@Override
	public boolean modifyDiet(Diet diet) {
		// 잘 수정되었다면 true 반환
		return dietDao.updateDiet(diet) > 0;
	}

	// 식단 삭제
	@Override
	public void removeDiet(int no) {
		dietDao.deleteDiet(no);

	}

	// 하루 식단 기록 조회
	@Override
	public List<Diet> getDietOneday(String loginUserId, String regDate) {
		return dietDao.selectDietOneday(loginUserId, regDate);
	}

	// 하루 중 아침 식단 조회
	@Override
	public List<Diet> getDietOnedayBreakfast(String loginUserId, String regDate) {
		return dietDao.selectDietOnedayBreakfast(loginUserId, regDate);
	}

	// 하루 중 점심 식단 조회
	@Override
	public List<Diet> getDietOnedayLunch(String loginUserId, String regDate) {
		return dietDao.selectDietOnedayLunch(loginUserId, regDate);
	}

	// 하루 중 저녁 식단 조회
	@Override
	public List<Diet> getDietOnedayDinner(String loginUserId, String regDate) {
		return dietDao.selectDietOnedayDinner(loginUserId, regDate);
	}

	// 하루 중 간식 식단 조회
	@Override
	public List<Diet> getDietOnedaySnack(String loginUserId, String regDate) {
		return dietDao.selectDietOnedaySnack(loginUserId, regDate);
	}
	
	// 식단 하나 조회
	@Override
	public Diet getDietOne(int no) {
		return dietDao.selectDietOne(no);
	}

}

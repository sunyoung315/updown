package com.ssafy.updown.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.updown.model.dao.ExerciseDao;
import com.ssafy.updown.model.dto.Exercise;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	
	@Autowired
	private ExerciseDao eDao;

	@Override
	public void upload(Exercise exercise) {
		eDao.insert(exercise);
	}

	@Override
	public List<Exercise> getList(String loginUserId, String regDate) {
		return eDao.selectList(loginUserId, regDate);
	}

	@Override
	public Exercise getOne(int exerciseNo) {
		return eDao.selectOne(exerciseNo);
	}

	@Override
	public void modify(Exercise exercise) {
		eDao.update(exercise);
	}

	@Override
	public void remove(int exerciseNo) {
		eDao.delete(exerciseNo);
	}

}

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
	public List<Exercise> getList(String loginUserId) {
		return eDao.selectList(loginUserId);
	}

	@Override
	public Exercise getOne(int exerciseNo) {
		return eDao.selectOne(exerciseNo);
	}

	@Override
	public void modify(int exerciseNo) {
		eDao.update(exerciseNo);
	}

	@Override
	public void remove(int exerciseNo) {
		eDao.delete(exerciseNo);
	}

}

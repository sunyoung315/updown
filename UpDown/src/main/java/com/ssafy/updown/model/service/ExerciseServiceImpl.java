package com.ssafy.updown.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.updown.model.dao.ExerciseDao;
import com.ssafy.updown.model.dto.Exercise;
import com.ssafy.updown.model.dto.ExerciseInfo;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	
	@Autowired
	private ExerciseDao eDao;

	@Override
	public void uploadExercise(Exercise exercise) {
		eDao.insertExercise(exercise);
	}

	@Override
	public List<Exercise> getExerciseList(String loginUserId, String regDate) {
		return eDao.selectExerciseList(loginUserId, regDate);
	}

	@Override
	public Exercise getOneExercise(int exerciseNo) {
		return eDao.selectOneExercise(exerciseNo);
	}

	@Override
	public void modifyExercise(Exercise exercise) {
		eDao.updateExercise(exercise);
	}

	@Override
	public void removeExercise(int exerciseNo) {
		eDao.deleteExercise(exerciseNo);
	}

	@Override
	public List<ExerciseInfo> getExerciseInfoList(String word) {
		return eDao.selectExerciseInfoList(word);
	}

	@Override
	public ExerciseInfo getExerciseInfo(String type) {
		return eDao.selectExerciseInfo(type);
	}

}

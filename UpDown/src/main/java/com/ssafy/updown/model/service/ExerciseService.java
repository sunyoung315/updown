package com.ssafy.updown.model.service;

import java.util.List;

import com.ssafy.updown.model.dto.Exercise;

public interface ExerciseService {
	//오늘의 운동 등록하기
	public void uploadExercise(Exercise exercise);
	
	//해당 일자에 등록된 운동 기록 리스트 가져오기 
	public List<Exercise> getExerciseList(String loginUserId, String regDate);
	
	//선택한 운동 기록 1개 가져오기
	public Exercise getOneExercise(int exerciseNo);
	
	//선택한 운동 기록 수정 
	public void modifyExercise(Exercise exercise);
	
	//선택한 운동 기록 삭제 
	public void removeExercise(int exerciseNo);
}

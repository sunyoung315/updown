package com.ssafy.updown.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.updown.model.dto.Exercise;
import com.ssafy.updown.model.dto.ExerciseInfo;

public interface ExerciseDao {
	//오늘의 운동 등록하기
	public void insertExercise(Exercise exercise);
	
	//해당 일자에 등록된 운동 기록 리스트 가져오기 
	public List<Exercise> selectExerciseList(@Param("loginUserId") String loginUserId, @Param("regDate") String regDate);
	
	//선택한 운동 기록 1개 가져오기
	public Exercise selectOneExercise(int exerciseNo);
	
	//선택한 운동 기록 수정 
	public void updateExercise(Exercise exercise);
	
	//선택한 운동 기록 삭제 
	public void deleteExercise(int exerciseNo);
	
	//입력한 키워드를 포함하는 운동명의 운동 정보 가져오기
	public List<ExerciseInfo> selectExerciseInfoList(String word);
	
	//운동명이 동일한 운동 정보 가져오기
	public ExerciseInfo selectExerciseInfo(String type);
}

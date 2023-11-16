package com.ssafy.updown.model.service;

import java.util.List;

import com.ssafy.updown.model.dto.User;

public interface UserService {
	// 전체 사용자 가져오기
	public List<User> getAllUsers();

	// 사용자 1명 가져오기
	public User getOneUser(String id);

	// 회원가입
	public void signUp(User user);

	// 사용자 정보 수정
	public void modifyUser(User user);

	// 사용자 삭제
	public void removeUser(String id);

}

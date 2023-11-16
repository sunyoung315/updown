package com.ssafy.updown.model.dao;

import java.util.List;

import com.ssafy.updown.model.dto.User;

public interface UserDao {
	//전체 사용자 가져오기 
	public List<User> selectAllUsers();
	
	//사용자 1명 가져오기 
	public User selectOneUser(String id);
	
	//회원가입 
	public void insertUser(User user);
	
	//사용자 정보 수정 
	public void updateUser(User user);
	
	//사용자 삭제 
	public void deleteUser(String id);
	
}

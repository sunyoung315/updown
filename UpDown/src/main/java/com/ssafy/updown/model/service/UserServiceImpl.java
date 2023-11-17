package com.ssafy.updown.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.updown.model.dao.UserDao;
import com.ssafy.updown.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao uDao;
	
	@Override
	public List<User> getAllUsers() {
		return uDao.selectAllUsers();
	}

	@Override
	public User getOneUser(String id, String password) {
		return uDao.selectOneUser(id, password);
	}

	@Override
	public void signUp(User user) {
		uDao.insertUser(user);
	}

	@Override
	public void modifyUser(User user) {
		uDao.updateUser(user);
	}

	@Override
	public void removeUser(String id) {
		uDao.deleteUser(id);
	}
	
}

package com.JFSD.SDP.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JFSD.SDP.Model.User;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository userRepository;

	@Override
	public String addUser(User u) {
		
		userRepository.save(u);
		return "User added successfully ";
	}

	@Override
	public User userLogin(String email, String password) {
		
		return userRepository.userLogin(email, password);
	}

}

package com.JFSD.SDP.Services;

import com.JFSD.SDP.Model.User;

public interface UserService 
{
	public String addUser(User u);
	public User userLogin(String email,String password);
	
}

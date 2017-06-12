package com.za.shadrack.service;

import java.util.List;

import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTO;

public interface UserService {

	public UserTO save(UserTO userTO) throws MyException;
	
	public UserTO update(UserTO userTO) throws MyException;
	
	public UserTO getByUserName(String userName) throws MyException;
	
	public List<UserTO> getAll() throws MyException;
	
	public List<UserTO> getAllInDuration(int durationInMinutes) throws MyException;
	
	public void remove(UserTO userTO) throws MyException;
	
	public String authenticate(UserTO userTO) throws MyException;
	
	public void logout(long userId) throws MyException;
	
}

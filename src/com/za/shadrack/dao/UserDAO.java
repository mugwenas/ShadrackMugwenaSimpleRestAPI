package com.za.shadrack.dao;

import java.util.List;

import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTO;

public interface UserDAO {
	
	public UserTO create(UserTO userTO) throws MyException;
	
	public UserTO update(UserTO userTO) throws MyException;
	
	public UserTO findByUserName(String userName) throws MyException;
	
	public UserTO findById(long id) throws MyException;
	
	public UserTO findByUserNameAndPassword(String userName, String password) throws MyException;
	
	public List<UserTO> findAll() throws MyException;
	
	public void delete(UserTO userTO) throws MyException;

}

package com.za.shadrack.dao;

import java.util.List;

import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTokenTO;

public interface UserTokenDAO {
	
    public UserTokenTO create(UserTokenTO userTokenTO) throws MyException;
	
	public UserTokenTO update(UserTokenTO userTokenTO) throws MyException;
	
	public UserTokenTO findByUserId(long id) throws MyException;
	
	public UserTokenTO findByToken(String token) throws MyException;
	
	public List<UserTokenTO> findAll() throws MyException;
		
	public void delete(UserTokenTO userTokenTO) throws MyException;

}

package com.za.shadrack.service;

import java.util.List;

import com.za.shadrack.bo.UserBO;
import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTO;

public class UserServiceImpl implements UserService {
	/**
	 * @author Shadrack.Mugwena
	 */

	private UserBO userBO;
	
	public UserServiceImpl(UserBO userBO) {
		this.userBO = userBO;
	}
	
	@Override
	public UserTO save(UserTO userTO) throws MyException {
		return this.userBO.save(userTO);
	}

	@Override
	public UserTO update(UserTO userTO) throws MyException {
		return this.userBO.update(userTO);
	}

	@Override
	public UserTO getByUserName(String userName) throws MyException {
		return this.userBO.getByUserName(userName);
	}

	@Override
	public List<UserTO> getAll() throws MyException {
		return this.userBO.getAll();
	}
	
	@Override
	public List<UserTO> getAllInDuration(int durationInMinutes) throws MyException {
		return this.userBO.loginInDuration(durationInMinutes);
	}

	@Override
	public void remove(UserTO userTO) throws MyException {
		this.userBO.remove(userTO);
		
	}

	@Override
	public String authenticate(UserTO userTO) throws MyException {
		return this.userBO.authenticate(userTO.getUsername(), userTO.getPassword());
	}

	@Override
	public void logout(long userId) throws MyException {
		this.userBO.logout(userId);
		
	}
	
}

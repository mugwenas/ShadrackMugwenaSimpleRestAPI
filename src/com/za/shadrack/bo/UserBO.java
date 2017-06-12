package com.za.shadrack.bo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.za.shadrack.dao.UserDAO;
import com.za.shadrack.dao.UserTokenDAO;
import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTO;
import com.za.shadrack.to.UserTokenTO;

public class UserBO {
	
	private long id;
	private String username;
	private char[] password;
	private String phone;
	
	private UserDAO userDAO;
	private UserTokenDAO userTokenDAO;
	
	public UserBO(UserDAO userDAO, UserTokenDAO userTokenDAO) {
		this.userDAO = userDAO;
		this.userTokenDAO = userTokenDAO;
	}
	
	public String authenticate(String username, String password) throws MyException {
       UserTO userTO = this.userDAO.findByUserNameAndPassword(username, password);
       if (null == userTO) {
    	   throw new MyException("UNAUTHORIZED_USER");
       }
             
       String token = issueToken();
       
       UserTokenTO userTokenTO = new UserTokenTO();
       userTokenTO.setUserId(userTO.getId());
       userTokenDAO.delete(userTokenTO);
       
       userTokenTO.setToken(token);
       userTokenTO.setExpriryDate(new Date());
              
       userTokenDAO.create(userTokenTO);
       
       return token;
    }
	
	public void logout(long userId) {
		UserTokenTO userTokenTO = new UserTokenTO();
		userTokenTO.setUserId(userId);
		this.userTokenDAO.delete(userTokenTO);		
	}
	
	public List<UserTO> loginInDuration(int durationInMinutes) throws MyException {
		List<UserTokenTO> userTokenTOs = this.userTokenDAO.findAll();
		List<UserTokenTO> userTokenTOsForDuration = new ArrayList<>();
		List<UserTO> userTOs = new ArrayList<>();
		for (UserTokenTO userTokenTO : userTokenTOs) {
			
			Date previous = userTokenTO.getExpriryDate();
			long previousMinutes = previous.getTime()/60000 ;
			
			long nowMinutes = new Date().getTime()/60000;
			
			if ((nowMinutes - previousMinutes ) <=  durationInMinutes) {
				userTokenTOsForDuration.add(userTokenTO);
			}
		}
		for (UserTokenTO userTokenTO : userTokenTOsForDuration) {
			userTOs.add(this.userDAO.findById(userTokenTO.getUserId()));
		}
		return userTOs;
	}
	
	public void validateToken(String token) throws MyException {
		UserTokenTO userTokenTO = this.userTokenDAO.findByToken(token);
		
		Date previous = userTokenTO.getExpriryDate();
		long previousMinutes = previous.getTime()/60000 ;
		
		long nowMinutes = new Date().getTime()/60000;
		
		if ((nowMinutes - previousMinutes ) >  3) {
			throw new MyException("Token has Expired...");
		}
		
	}

    public String issueToken() {
        Random random = new SecureRandom();
    	String token = new BigInteger(130, random).toString(32);
    	
    	return token;
    }

   	public UserTO save(UserTO userTO) throws MyException {
		return this.userDAO.create(userTO);
	}

	public UserTO update(UserTO userTO) throws MyException {
		return this.userDAO.update(userTO);
	}
	
	public UserTO getByUserName(String userName) throws MyException {
		return this.userDAO.findByUserName(userName);
	}

	public List<UserTO> getAll() throws MyException {
		return this.userDAO.findAll();
	}

	public void remove(UserTO userTO) throws MyException {
		this.userDAO.delete(userTO);
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}

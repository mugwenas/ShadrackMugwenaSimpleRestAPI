package com.za.shadrack.to;

import java.util.Date;

public class UserTokenTO {
	/**
	 * @author Shadrack.Mugwena
	 */
	
	private long id;
	private String token;
	private Date expriryDate;
	private long userId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpriryDate() {
		return expriryDate;
	}
	public void setExpriryDate(Date expriryDate) {
		this.expriryDate = expriryDate;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

}

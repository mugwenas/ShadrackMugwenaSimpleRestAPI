package com.za.shadrack.to;

import java.io.Serializable;

public class UserTO implements Serializable {

	/**
	 *@author Shadrack.Mugwena 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String username;
	private String password;
	private String phone;
		
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}

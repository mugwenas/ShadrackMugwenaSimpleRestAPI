package com.za.shadrack.endpoint;

import java.util.ArrayList;
import java.util.List;

import com.za.shadrack.to.UserTO;

public class UserConverter {

	public static UserResource convert(UserTO userTO) {
		UserResource userResource = new UserResource();
		userResource.setId(userTO.getId());
		userResource.setUsername(userTO.getUsername());
		userResource.setPassword(userTO.getPassword().toString());
		userResource.setPhone(userTO.getPhone());
		
		return userResource;
	}
	
	public static List<UserResource> convertToUserResources(List<UserTO> userTOs) {
		List<UserResource> resources = new ArrayList<>();
		for (UserTO userTO : userTOs) {
			resources.add(UserConverter.convert(userTO));
		}
		return resources;
	}
	
	public static List<UserTO> convertToUserTOs(List<UserResource> resources) {
		List<UserTO> userTOs = new ArrayList<>();
		for (UserResource userResource : resources) {
			userTOs.add(UserConverter.convert(userResource));
		}
		return userTOs;
	}
	
	public static UserTO convert(UserResource userResource){
		UserTO userTO = new UserTO();
		userTO.setUsername(userResource.getUsername());
		userTO.setPhone(userResource.getPhone());
		userTO.setPassword(userResource.getPassword());
		return userTO;
	}
	
}

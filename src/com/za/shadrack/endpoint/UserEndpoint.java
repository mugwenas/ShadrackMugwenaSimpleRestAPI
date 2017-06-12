package com.za.shadrack.endpoint;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.za.shadrack.exception.MyException;
import com.za.shadrack.service.UserService;
import com.za.shadrack.to.UserTO;

@Resource
@Path("/users")
public class UserEndpoint {
	/**
	 * @author Shadrack.Mugwena
	 */

	private UserService userService;
	
	public UserEndpoint(UserService userService) {
		this.userService = userService;
	}
	
	@PUT
	@Path("/user/add")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response addUser(UserResource userResource) throws MyException {
		UserTO userTO = null;
		UserTO addedUser = null;
		UserResource resource = null;
	
		try{
			userTO = UserConverter.convert(userResource);
			addedUser = this.userService.save(userTO);
			resource = UserConverter.convert(addedUser);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.CREATED).entity(resource).build();	
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAll() {
		List<UserResource> resources = UserConverter.convertToUserResources(this.userService.getAll());
		return Response.ok(resources).build();
	}
	
	@GET
	@Path("/duration")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAllInDuration() {
		List<UserResource> resources = UserConverter.convertToUserResources(this.userService.getAllInDuration(5));
		return Response.ok(resources).build();
	}
	
	@POST
	@Path("/user/logout/{id}") 
	@Produces({MediaType.APPLICATION_JSON})
	public void logout(@PathParam("id") String id) throws MyException {
		
		this.userService.logout(Long.valueOf(id));
	}
		
	@POST
	@Path("/user/login")
	public Response login(UserResource resource) throws MyException {
		
		String token = null;
		UserTO userTO = null;
		TokenResponse tokenResponse = null;
		try {
			token = this.userService.authenticate(UserConverter.convert(resource));
			userTO = this.userService.getByUserName(resource.getUsername());
		
		    tokenResponse = new TokenResponse();
		    tokenResponse.id = userTO.getId();
		    tokenResponse.token = token;
		 } catch (Exception e) {
			 return Response.status(Response.Status.UNAUTHORIZED).build();
		 }
		
		return Response.status(Response.Status.OK).entity(tokenResponse).build();
		
	}
	
	@XmlRootElement(name = "token")
	private static class TokenResponse {
		private long id;
		private String token;
		
		@XmlElement
		public long getId() {
			return id;
		}
		@XmlElement
		public String getToken() {
			return token;
		}
			
	}
}

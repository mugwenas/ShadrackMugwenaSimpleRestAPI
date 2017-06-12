package com.za.shadrack.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTO;

public class UserDAOImpl implements UserDAO {
	/**
	 * @author Shadrack.Mugwena
	 */

	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public UserTO create(UserTO userTO) throws MyException {
		long id = 0;
		String Sql = "insert into USERS (ID, USERNAME, PASSWORD, PHONE) values (?, ?, ?, ?) ";
		try{
			id = getNextSequenceValue("USERS_SEQ");
			jdbcTemplate.update(Sql, new Object[]{id, userTO.getUsername(),
					userTO.getPassword().toString(),
					userTO.getPhone()});
		} catch (DataAccessException e) {
			throw new MyException("Error inserting user: " + userTO.getUsername());
		}
		userTO.setId(id);
		
		return userTO;
	}

	@Override
	public UserTO update(UserTO userTO) throws MyException {
		String Sql = "update USERS set PHONE = ? where USERNAME = ?";
		try{
			jdbcTemplate.update(Sql, new Object[]{userTO.getPhone(), userTO.getUsername()});
		} catch (DataAccessException e) {
			throw new MyException("Error updating user: " + userTO.getUsername());
		}
		return userTO;
	}

	@Override
	public UserTO findByUserName(String userName) throws MyException {
		String Sql = "select * from USERS where UPPER(USERNAME) = ? " ;
		UserTO userTO = null;
		try{
			List<UserTO> userTOList = jdbcTemplate.query(Sql, new Object[]{ userName.trim().toUpperCase() }, new UserMapper());
			if (userTOList.size() > 0) {
				userTO = userTOList.get(0);
			}
		} catch (DataAccessException e) {
			throw new MyException("Error getting user: " + userName);
		}
		return userTO;
	}


	@Override
	public UserTO findById(long id) throws MyException {
		String Sql = "select * from USERS where ID = ? " ;
		UserTO userTO = null;
		try{
			List<UserTO> userTOList = jdbcTemplate.query(Sql, new Object[]{ id }, new UserMapper());
			if (userTOList.size() > 0) {
				userTO = userTOList.get(0);
			}
		} catch (DataAccessException e) {
			throw new MyException("Error getting user: " + id);
		}
		return userTO;
	}

	@Override
	public UserTO findByUserNameAndPassword(String userName, String password) throws MyException {
		String Sql = "select * from USERS where UPPER(USERNAME) = ? AND UPPER(PASSWORD) = ? " ;
		UserTO userTO = null;
		try{
			List<UserTO> userTOList = jdbcTemplate.query(Sql, new Object[]{ userName.trim().toUpperCase() ,
					password.trim().toUpperCase() }, new UserMapper());
			if (userTOList.size() > 0) {
				userTO = userTOList.get(0);
			}
		} catch (DataAccessException e) {
			throw new MyException("Error getting user: " + userName);
		}
		return userTO;
	}
	@Override
	public List<UserTO> findAll() throws MyException {
		String Sql = "select * from USERS" ;
		List<UserTO> userTOList = null;
		try{
			userTOList = jdbcTemplate.query(Sql, new UserMapper());
		} catch (DataAccessException e) {
			throw new MyException("Error getting users");
		}
		return userTOList;
	}

	@Override
	public void delete(UserTO userTO) throws MyException {
		String Sql = "delete from USERS where USERNAME = ? " ;
		try{
			jdbcTemplate.update(Sql, new Object[]{userTO.getUsername()});
		} catch (DataAccessException e) {
			throw new MyException("Error deleting user: " + userTO.getUsername());
		}
		
	}
	
	private long getNextSequenceValue(String sequenceName) {
		final StringBuilder builder = new StringBuilder("SELECT ");
		builder.append(sequenceName);
		builder.append(".NextVal FROM DUAL");

		return jdbcTemplate.queryForObject(builder.toString(), Long.class);
	}
	
	private static class UserMapper implements RowMapper<UserTO> {

		@Override
		public UserTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserTO userTO = new UserTO();
			userTO.setId(rs.getLong("ID"));
			userTO.setUsername(rs.getString("USERNAME"));
			userTO.setPassword(rs.getString("PASSWORD"));
			userTO.setPhone(rs.getString("PHONE"));
			return userTO;
		}
		
	}

}

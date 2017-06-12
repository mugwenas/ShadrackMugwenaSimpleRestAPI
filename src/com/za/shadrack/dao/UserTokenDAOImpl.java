package com.za.shadrack.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.za.shadrack.exception.MyException;
import com.za.shadrack.to.UserTokenTO;

public class UserTokenDAOImpl implements UserTokenDAO {
	/**
	 * @author Shadrack.Mugwena
	 */
	
	private JdbcTemplate jdbcTemplate;
	
	public UserTokenDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UserTokenTO create(UserTokenTO userTokenTO) throws MyException {
		long id = 0;
		String Sql = "insert into USER_TOKENS (ID, TOKEN, EXPIRY_DATE, USER_ID) values (?, ?, ?, ?) ";
		
		try{
			id = getNextSequenceValue("USER_TOKENS_SEQ");
			jdbcTemplate.update(Sql, new Object[]{id, userTokenTO.getToken(),
					userTokenTO.getExpriryDate(),
					userTokenTO.getUserId()});
		} catch (DataAccessException e) {
			throw new MyException("Error inserting USER_TOKENS for user: " + userTokenTO.getToken());
		}
		userTokenTO.setId(id);
		
		return userTokenTO;
	}

	@Override
	public UserTokenTO update(UserTokenTO userTokenTO) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTokenTO findByUserId(long id) throws MyException {
		String Sql = "select * from USER_TOKENS where USER_ID = ? " ;
		UserTokenTO userTokenTO = null;
		try{
			List<UserTokenTO> userTokenTOList = jdbcTemplate.query(Sql, new Object[]{ id }, new UserTokenMapper());
			if (userTokenTOList.size() > 0) {
				userTokenTO = userTokenTOList.get(0);
			}
		} catch (DataAccessException e) {
			throw new MyException("Error getting userToken with id: " + id);
		}
		return userTokenTO;
	}

	@Override
	public void delete(UserTokenTO userTokenTO) throws MyException {
		String Sql = "delete from USER_TOKENS where USER_ID = ? " ;
		try{
			jdbcTemplate.update(Sql, new Object[]{ userTokenTO.getUserId() });
		} catch (DataAccessException e) {
			throw new MyException("Error deleting userToken: " + userTokenTO.getToken());
		}
		
	}
	
	@Override
	public UserTokenTO findByToken(String token) throws MyException {
		String Sql = "select * from USER_TOKENS where UPPER(TOKEN) = ? " ;
		UserTokenTO userTokenTO = null;
		try{
			List<UserTokenTO> userTokenTOList = jdbcTemplate.query(Sql, new Object[]{ token.trim().toUpperCase() }, new UserTokenMapper());
			if (userTokenTOList.size() > 0) {
				userTokenTO = userTokenTOList.get(0);
			}
		} catch (DataAccessException e) {
			throw new MyException("Error getting userToken");
		}
		return userTokenTO;
	}
	
	@Override
	public List<UserTokenTO> findAll() throws MyException {
		String Sql = "select * from USER_TOKENS" ;
		List<UserTokenTO> userTokenTOList = null;
		try{
			userTokenTOList = jdbcTemplate.query(Sql, new UserTokenMapper());
		} catch (DataAccessException e) {
			throw new MyException("Error finding tokens");
		}
		return userTokenTOList;
	}
	
	private long getNextSequenceValue(String sequenceName) {
		final StringBuilder builder = new StringBuilder("SELECT ");
		builder.append(sequenceName);
		builder.append(".NextVal FROM DUAL");

		return jdbcTemplate.queryForObject(builder.toString(), Long.class);
	}
	
	private static class UserTokenMapper implements RowMapper<UserTokenTO> {

		@Override
		public UserTokenTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserTokenTO userTokenTO = new UserTokenTO();
			userTokenTO.setId(rs.getLong("ID"));
			userTokenTO.setToken(rs.getString("TOKEN"));
			userTokenTO.setExpriryDate(rs.getTimestamp("EXPIRY_DATE"));
			userTokenTO.setUserId(rs.getLong("USER_ID"));
			return userTokenTO;
		}
		
	}

	
}

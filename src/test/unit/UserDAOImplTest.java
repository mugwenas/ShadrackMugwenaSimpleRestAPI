package test.unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.za.shadrack.dao.UserDAOImpl;
import com.za.shadrack.to.UserTO;

public class UserDAOImplTest {
	
	private EmbeddedDatabase database;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp()	{ 
		database = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.setName("MyInMemoryDatabase")
				.addScript("classpath:com/za/shadrack/dao/db/sql/oracle-syntax.sql")
				.addScript("classpath:com/za/shadrack/dao/db/sql/create-db.sql")
				.build();
		
		jdbcTemplate = new JdbcTemplate(database);
		
	}
	@Test
	public void test_Database_Syntax_Is_Switched_To_Oracle() {
		int actualSum = jdbcTemplate.queryForObject("select 1+1 from dual", Integer.class);
		assertEquals(2, actualSum);
	}
	@Test
	public void testCreateUser() {
		UserTO userTO = new UserTO();
		userTO.setUsername("Shadrack");
		userTO.setPassword("password".toCharArray());
		userTO.setPhone("111-111-1111");
		
		UserDAOImpl userDAOImpl = new UserDAOImpl(jdbcTemplate);
		UserTO createUserTO = userDAOImpl.create(userTO);
		assertEquals("Shadrack", createUserTO.getUsername());
		
	}
	
	@Test
	public void testUpdateUser() {
		UserTO userTO = new UserTO();
		userTO.setUsername("Shadrack");
		userTO.setPassword("password".toCharArray());
		userTO.setPhone("111-111-1111");
		
		UserDAOImpl userDAOImpl = new UserDAOImpl(jdbcTemplate);
		UserTO createUserTO = userDAOImpl.create(userTO);
		assertEquals("Shadrack", createUserTO.getUsername());
		
		userTO.setPhone("222-222-2222");
		UserTO updatedUserTO = userDAOImpl.update(userTO);
		
		assertEquals("222-222-2222", updatedUserTO.getPhone());
		
	}
	
	@Test
	public void testFindByUserName() {
		UserTO userTO = new UserTO();
		userTO.setUsername("Shadrack");
		userTO.setPassword("password".toCharArray());
		userTO.setPhone("111-111-1111");
		
		UserDAOImpl userDAOImpl = new UserDAOImpl(jdbcTemplate);
		userDAOImpl.create(userTO);
		
		assertEquals("111-111-1111", userDAOImpl.findByUserName("Shadrack").getPhone());
	}
	
	@Test
	public void testFindAll() {
		UserDAOImpl userDAOImpl = new UserDAOImpl(jdbcTemplate);
		
		UserTO userTO = new UserTO();
		userTO.setUsername("Shadrack");
		userTO.setPassword("password".toCharArray());
		userTO.setPhone("111-111-1111");
		
		UserTO userTO2 = new UserTO();
		userTO2.setUsername("Bob");
		userTO2.setPassword("password".toCharArray());
		userTO2.setPhone("222-222-2222");
		
		
		userDAOImpl.create(userTO);
		userDAOImpl.create(userTO2);
				
		assertEquals(2, userDAOImpl.findAll().size());
	}
	
	@Test
	public void testDeleteUser() {
		UserTO userTO = new UserTO();
		userTO.setUsername("Shadrack");
		userTO.setPassword("password".toCharArray());
		userTO.setPhone("111-111-1111");
		
		UserDAOImpl userDAOImpl = new UserDAOImpl(jdbcTemplate);
		UserTO createUserTO = userDAOImpl.create(userTO);
		assertNotNull(createUserTO);;
		
		userDAOImpl.delete(userTO);
		
		assertNull(userDAOImpl.findByUserName("Shadrack"));
		
	}
	
	@After
	public void tearDown(){
		database.shutdown();
	}
	

}

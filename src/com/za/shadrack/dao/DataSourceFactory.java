package com.za.shadrack.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DataSourceFactory {
	/**
	 * @author Shadrack.Mugwena
	 */
		
	public DataSource getHSQLDataSource() {
		EmbeddedDatabase database = new EmbeddedDatabaseBuilder()
					.setType(EmbeddedDatabaseType.HSQL)
					.setName("MyInMemoryDatabase")
					.addScript("classpath:com/za/shadrack/dao/db/sql/oracle-syntax.sql")
					.addScript("classpath:com/za/shadrack/dao/db/sql/create-db.sql")
					.build();
					
		return database;
	}

}

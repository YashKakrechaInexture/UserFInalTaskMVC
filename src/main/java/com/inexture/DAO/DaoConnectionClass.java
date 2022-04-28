package com.inexture.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Dao connection singleton class. Returns single instance of the database connection.
 * @author Yash
 *
 */
public final class DaoConnectionClass {
	
	
	/**
	 * Stores Connection of database
	 */
	private static Connection conn = null;
	
	/**
	 * Private constructor, to avoid creating object.
	 */
	private DaoConnectionClass() {}
	
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(DaoConnectionClass.class);
	
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String resourceName = "db.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Properties properties = new Properties();  
			
			try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
				properties.load(resourceStream);  
				String url = properties.getProperty("url");	
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");
				
				conn = DriverManager.getConnection(url,user,password);
				
				LOG.info("Database connection initialized.");
			}catch(Exception e) {
				LOG.fatal("Cannot create Database connection. Exception : "+e);
			}
		}catch(Exception e) {
			LOG.fatal("Cannot create Database connection. Exception : "+e);
		}
	}
	
	
	/**
	 * Returns a single instance of the mysql database 
	 * @return conn - Stores Connection of database
	 */
	public static Connection getConnection() {
		LOG.debug("Returned Database connection");
		return conn; 
	}
}

package com.tsuyu.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to handle all database related operations
 * @author
 *
 */
public class ConnectionDB
{	
	/**
	 * database connection object
	 */
	private Connection conn;
	
	/**
	 * database driver name
	 */
	private static final String dbDriver = "com.mysql.jdbc.Driver";
	
	/**
	 * database name
	 */
	private static final String dbName = "tsuyu";
	
	/**
	 * database user name
	 */
	private static final String dbUserName = "root";
	
	/**
	 * database password
	 */
	private static final String dbPassword = "123456";
	
	/**
	 * database connection URL
	 */
	private static final String dbURL = "jdbc:mysql://localhost/"+dbName+"?user="+dbUserName+"&password="+dbPassword;
	
	/**
	 * Constructor, load the database driver
	 * @throws ClassNotFoundException Class Not Found Exception
	 * @throws SQLException SQL Exception
	 */
	public ConnectionDB() throws ClassNotFoundException, SQLException
	{
		/*Load the database driver
		 * Note: this is automatically done in JDK 1.6,
		 * but no harm load again here*/
		//Class.forName(dbDriver);
	}
	
	/**
	 * Connect to the database
	 * @return database connection object
	 * @throws SQLException SQL Exception
	 * @throws ClassNotFoundException 
	 */
	public static Connection connectDB() throws SQLException, ClassNotFoundException
	{
		/*return the connection to the database*/
		//jdbc:mysql://localhost/sampledb?user=root&password=123456
		Class.forName(dbDriver);
		return DriverManager.getConnection(dbURL);
	}
	
	/**
	 * Closes the current database connection
	 * @throws SQLException SQL Exception
	 */
	public void closeDBConnection() throws SQLException
	{
		/*if the connection object still exists (not null), close it*/
		if(conn!=null)
			conn.close();
	}
}


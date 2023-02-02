/*
 * Perform the CRUD operation on student table present in enterprise_java database
 * CRUD => Create, Read, Update, Delete
 */

package app.jdbcUtil;

import java.sql.*;

public class JdbcUtilApp 
{
	private JdbcUtilApp() {}
	
	public static Connection getJdbcConnection() throws SQLException {
		// resource used in establishing the connection
		Connection connection = null;
		
		// Establish the connection
		String url = "jdbc:mysql://localhost:3306/enterprise_java";
		String user = "giantDB";
		String password = "Giant123DB";
		
		connection = DriverManager.getConnection(url, user, password);
		
		if(connection != null){
			return connection;
		}
		return connection;
	}
	
	public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) throws SQLException
	{
		// Close the connection
		if(resultSet != null)
			resultSet.close();
		if(statement != null)
			statement.close();
		if(connection != null)
			connection.close();
	}
	
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertQueryApp 
{
	public static void main(String[] args) 
	{	
		Connection connection = null;
		Statement statement = null;
		
		String url = "jdbc:mysql://localhost:3306/enterprise_java";
		String user = "giantDB";
		String password = "Giant123DB";

		// SQL insert query
		String insertQuery = "insert into student(`sid`,`sname`,`sage`,`subject`,`marks`) values(6,'sonny',17,'maths',42)";
		
		try {						
			// Establish the connection b/w java & mysql
			connection = DriverManager.getConnection(url, user, password);
			
			if(connection != null) {
				// Create a statement object
				statement = connection.createStatement();
			
				if(statement != null) {
					// send and execute the query
					int noOfrows = statement.executeUpdate(insertQuery);
					
					System.out.println("Number of rows affected, " + noOfrows);
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection != null)
					connection.close();
				if(statement != null)
					statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

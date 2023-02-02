import java.sql.*;
public class CreateQuery 
{
	public static void main(String[] args) 
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String createTable = 
		"create table employee(eid int, ename varchar(20), eage int,salary double)";
		
		int eid = 0;
		String ename = null;
		int eage = 0;
		double salary = 0.0;
		
		String url = "jdbc:mysql://localhost:3306/enterprise_java";
		String user = "giantDB";
		String password = "Giant123DB";
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			
			if(connection != null) {
				statement = connection.createStatement();
				
				if(statement != null) {
					int tableCreated = statement.executeUpdate(createTable);
					System.out.println("Number of rows affected, " + tableCreated);
				}
			}
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}
}

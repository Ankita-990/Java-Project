import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SelectQueryApp1 
{
	public static void main(String[] args) throws SQLException 
	{
		// 1. Load & register the Driver
//		Driver driver = new Driver();
//		DriverManager.deregisterDriver(driver);
		
		/*
		 *	From java 4.X Version => we don't have to register the Driver manually
		 *	This step is automatically done by JVM
		 *
		 *	How it is done?
		 *		- Frist it will check check the url(which DB vendor url is present) 
		 *		  which is passed by a user in Connection object
		 *		- JVM will go to that specific jar file (int this example it is MYSQL)
		 *		- Inside the .jar file there is one folder called "META-INF" 
		 *		- "META-INF -> services -> java.sql.Driver" in that file mysql Driver 
		 *		   class is already defined
		 *		- This class implements java.sql.Driver interface
		 */
		
		// 2. Establish the connection
		String url = "jdbc:mysql://localhost:3306/enterprise_java";
		String user = "giantDB";
		String password = "Giant123DB";
		Connection connection = DriverManager.getConnection(url, user, password);
		
		// 3. Create a statement object
		Statement statement = connection.createStatement();
		
		// 4. Execute the query
		String sqlSelectQuery = "SELECT sid,sname,sage,subject,marks FROM student";
		ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
		
		// 5. Process the query
		System.out.println("sid\tsname\tsage\tsubject\tmarks");
		System.out.println("=====================================");
		while(resultSet.next())
		{
			int sid = resultSet.getInt("sid");
			String sname = resultSet.getString("sname");
			int sage = resultSet.getInt("sage");
			String subject = resultSet.getString("subject");
			int marks = resultSet.getInt("marks");
			
			System.out.println(sid + "\t" + sname + "\t" + sage + "\t" + subject + "\t" + marks);
		}
		
		// 6. Close the resources
		resultSet.close();
		statement.close();
		connection.close();
		
		
		
		
	} 
}

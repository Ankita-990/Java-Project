// Select/Read Operation

package app.dynamicInput;

import java.sql.*;
import app.jdbcUtil.*;

public class SelectQuery 
{
	public void select()
	{
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		
//		Scanner scan = new Scanner(System.in);
		
		int eid = 0;
		String ename = null;
		int eage = 0;
		double salary = 0.0;
		
		String selectQuery = "select eid, ename, eage, salary from employee";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstat = connection.prepareStatement(selectQuery);
				
				if(pstat != null) {
					resultSet = pstat.executeQuery();
					
					System.out.println("eid\tename\teage\tsalary");
					while(resultSet.next()) {
						eid = resultSet.getInt("eid");
						ename = resultSet.getNString("ename");
						eage = resultSet.getInt("eage");
						salary = resultSet.getDouble("salary");
						
						System.out.println(eid + "\t" + ename + "\t" + eage + "\t" + salary);
					}
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			try {
				JdbcUtilApp.closeConnection(resultSet, pstat, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

// Delete Operation

package app.dynamicInput;

import java.sql.*;
import java.util.Scanner;
import app.jdbcUtil.JdbcUtilApp;

public class DeleteQuery 
{
	public void delete()
	{
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Which employee you want to delete");
		int eid = scan.nextInt();
		
		String deleteQuery = "delete from employee where eid=?";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstat = connection.prepareStatement(deleteQuery);
				
				if(pstat != null) {
					pstat.setInt(1, eid);
					int rowsDeleted = pstat.executeUpdate();
					System.out.println("Query OK," + rowsDeleted + " rows deleted");
				}
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			try {
				JdbcUtilApp.closeConnection(resultSet, pstat, connection);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
}

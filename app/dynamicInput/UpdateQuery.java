// Update Operation

package app.dynamicInput;

import java.sql.*;
import java.util.Scanner;

import app.jdbcUtil.JdbcUtilApp;

public class UpdateQuery 
{
	public void update()
	{
		// Resources used in JDBC API
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		
		Scanner scan = new Scanner(System.in);
		
		
		int eid = 0;
		String ename = null;
		int eage = 0;
		double salary = 0.0;
		
		String updateQuery = null;
		
		System.out.println("What you want to update");
		String whatToUpdate = scan.next();
		
		System.out.println("Enter emplployee id");
		eid = scan.nextInt();
		
		switch(whatToUpdate) {
		case "ename":
			System.out.println("Enter employee name : ");
			ename = scan.next();
			updateQuery = String.format("update employee set ename='%s' where eid=%d", ename, eid);
			break;
		case "eage":
			System.out.println("Enter employee age : ");
			eage = scan.nextInt();
			updateQuery = String.format("update employee set eage=%d where eid=%d", eage, eid);
			break;
		case "salary":
			System.out.println("Enter employee salary : ");
			salary = scan.nextDouble();
			updateQuery = String.format("update employee set salary=%f where eid=%d", salary, eid);
			break;
		case "all":
			System.out.println("Enter employee name : ");
			ename = scan.next();
			System.out.println("Enter employee age : ");
			eage = scan.nextInt();
			System.out.println("Enter employee salary : ");
			salary = scan.nextDouble();
			updateQuery = String.format("update employee set ename='%s', eage=%d, salary=%f where eid=%d", ename, eage, salary, eid);
			break;
		}
		

		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstat = connection.prepareStatement(updateQuery);
				
				if(pstat != null) {
					int rowsUpdated = pstat.executeUpdate();
					System.out.println("Query OK, " + rowsUpdated + " rows updated");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			try {
				JdbcUtilApp.closeConnection(resultSet, pstat, connection);
				scan.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

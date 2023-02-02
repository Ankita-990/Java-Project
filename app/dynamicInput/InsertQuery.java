// Insert Operation

package app.dynamicInput;

import java.sql.*;	
import java.util.Scanner;

import app.jdbcUtil.JdbcUtilApp;

public class InsertQuery 
{
	public void insert() 
	{
		// Resources used in JDBC API
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		
		Scanner scan = new Scanner(System.in);
		
		// Employee info
		System.out.print("Enter employee id : ");
		int eid = scan.nextInt();
		System.out.print("Enter employee name : ");
		String ename = scan.next();
		System.out.print("Enter employee age : ");
		int eage = scan.nextInt();
		System.out.print("Enter employee salary : ");
		double salary = scan.nextDouble();
		
		String insertQuery = "insert into employee(`eid`,`ename`,`eage`,`salary`) values(?,?,?,?)";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();

			if(connection != null) {
				pstat = connection.prepareStatement(insertQuery);
				
				if(pstat != null) {
					pstat.setInt(1, eid);
					pstat.setString(2, ename);
					pstat.setInt(3, eage);
					pstat.setDouble(4, salary);
					
					int rowAffected = pstat.executeUpdate();
					System.out.println("Query OK, " + rowAffected + " rows affected");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtilApp.closeConnection(resultSet, pstat, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scan.close();
		}
		
		
		
	}
}

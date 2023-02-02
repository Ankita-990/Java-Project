package date.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import app.jdbcUtil.JdbcUtilApp;

public class RetrivalApp 
{
	public static void main(String[] args) 
	{
		Connection connection = null;
		PreparedStatement pstamt = null;
		ResultSet resultSet = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter name");
		String uname = scan.next();
		
		String selectQuery = "select * from userdata like name=%?";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstamt = connection.prepareStatement(selectQuery);
				
				if(pstamt != null) {
					pstamt.setString(1, uname);
					resultSet = pstamt.executeQuery();
					
					if(resultSet != null) {
						if(resultSet.next()) {
							String name = resultSet.getString(1);
							String address = resultSet.getString(2);
							String gender = resultSet.getString(3);
							java.sql.Date birthDate = resultSet.getDate(4);
							java.sql.Date joinDate = resultSet.getDate(5);
							java.sql.Date mairrageDate = resultSet.getDate(6);
							
							System.out.println("Name\tAddress\tGender\tDOB\tDOJ\tDOM");
							System.out.println(name + "\t" + address + "\t" + gender + "\t" + birthDate + "\t" + joinDate + "\t" + mairrageDate);
						}
						else {
							System.out.println("No records found for " + uname);
						}
					}
				}
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtilApp.closeConnection(resultSet, pstamt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

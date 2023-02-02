package date.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import app.jdbcUtil.JdbcUtilApp;

public class DateRetrivalApp 
{
	public static void main(String[] args) 
	{
		
		Connection connection = null;
		PreparedStatement pstamt = null;
		ResultSet resultSet = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter name :: ");
		String name = scan.next();
		
		String sqlSelectQuery = "select name, dob from userdata where name=?";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstamt = connection.prepareStatement(sqlSelectQuery);
				
				if(pstamt != null) {
					pstamt.setString(1, name);
					resultSet = pstamt.executeQuery();
				
				
					if(resultSet != null) {
						if(resultSet.next()) {
							String username = resultSet.getString(1);
							java.sql.Date dob = resultSet.getDate(2);
							System.out.println("SQL Date present in database is " + dob);
							SimpleDateFormat sdf = new SimpleDateFormat();
							String sdob = sdf.format(dob);
							System.out.println("Name is " + name);
							System.out.println("DOB is " + sdob);
						}
						else {
							System.out.println("No record found!");
						}
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtilApp.closeConnection(resultSet, pstamt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

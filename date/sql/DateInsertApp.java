package date.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import app.jdbcUtil.JdbcUtilApp;

public class DateInsertApp 
{
	public static void main(String[] args) throws ParseException 
	{
		
		Connection connection = null;
		PreparedStatement pstamt = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter name");
		String name = scan.next();
		System.out.println("Enter DOB (dd-mm-yyyy)");
		String sdob = scan.next();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate = sdf.parse(sdob);
		
		long time = uDate.getTime();
		
		java.sql.Date sqlDate = new java.sql.Date(time);
		
		System.out.println("String dob :: " + sdob);
		System.out.println("Util Date :: " + uDate);
		System.out.println("SQL Date :: " + sqlDate);
		
		String sqlInsertQuery = "insert into userdata(name, dob) values(?,?)";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstamt = connection.prepareStatement(sqlInsertQuery);
				
				if(pstamt != null) {
					pstamt.setString(1, name);
					pstamt.setDate(2, sqlDate);
					int rowsInserted = pstamt.executeUpdate();
					System.out.println("Number of rows inserted " + rowsInserted);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}

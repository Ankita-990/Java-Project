package date.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import app.jdbcUtil.JdbcUtilApp;

public class InsertionApp 
{
	public static void main(String[] args) throws ParseException 
	{
		Connection connection = null;
		PreparedStatement pstamt = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter user name");
		String uname = scan.next();
		System.out.println("Enter address");
		String address = scan.next();
		System.out.println("Enter gender");
		String gender = scan.next();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		
		System.out.println("Enter date of birth (dd-mm-yyyy)");
		String sdob = scan.next();
		System.out.println("Enter date of joining (dd-mm-yyyy)");
		String sdoj = scan.next();
		System.out.println("Enter date of mairrage (dd-mm-yyyy)");
		String sdom	= scan.next();
		
		java.util.Date dob = sdf.parse(sdob);		// Date of birth
		java.util.Date doj = sdf.parse(sdoj);		// Date of joining
		java.util.Date dom = sdf.parse(sdom);		// Date of mairrage
		
		long ldob = dob.getTime();
		long ldoj = doj.getTime();
		long ldom = dom.getTime();
		
		java.sql.Date sqlDob = new java.sql.Date(ldob);
		java.sql.Date sqlDoj = new java.sql.Date(ldoj);
		java.sql.Date sqlDom = new java.sql.Date(ldom);
		
		String insertQuery = "insert into userdata(name,address,gender,dob,doj,dom) values(?,?,?,?,?,?)";
		
		try {
			connection = JdbcUtilApp.getJdbcConnection();
			
			if(connection != null) {
				pstamt = connection.prepareStatement(insertQuery);
				
				if(pstamt != null) {
					pstamt.setString(1, uname);
					pstamt.setString(2, address);
					pstamt.setString(3, gender);
					pstamt.setDate(4, sqlDob);
					pstamt.setDate(5, sqlDoj);
					pstamt.setDate(6, sqlDom);
					
					int rowsInserted = pstamt.executeUpdate();
					System.out.println("Number of rows inserted " + rowsInserted);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				JdbcUtilApp.closeConnection(null, pstamt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

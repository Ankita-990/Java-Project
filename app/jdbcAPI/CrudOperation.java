/*
 * Perform the CRUD operation on student table present in enterprise_java database
 * CRUD => Create, Read, Update, Delete
 */

package app.jdbcAPI;

import java.sql.*;
import java.util.Scanner;

public class CrudOperation 
{
	public static void main(String[] args) 
	{
		// Scanner object to take input from user
		Scanner scan = new Scanner(System.in);
		
		// Creating Connection object 
		Connection connection = null;
		
		// Creating Statement object 
		Statement statement = null;
		
		// Creating ResultSet object
		ResultSet resultSet = null;
		
		// Details of mysql DB
		String url = "jdbc:mysql://localhost:3306/enterprise_java";
		String user = "giantDB";
		String password = "Giant123DB";

		// Student columns
		int sid;
		String sname;
		int sage;

		
		
		try {
			// Connecting to the Database
			connection = DriverManager.getConnection(url, user, password);
			
			if(connection != null) {
				//Creating the statement object
				statement = connection.createStatement();
				
				// is the statement object is created?
				if(statement != null) 
				{
					// select query to display student table
					String selectQuery = "select sid, sname, sage from student";
					resultSet = statement.executeQuery(selectQuery);
					
					if(resultSet != null) {
						// Performing CRUD Operation
						System.out.println("***** Perform the CRUD Operation *****");
						System.out.println("Press C to insert/create row in student");
						System.out.println("Press R to read row in student");
						System.out.println("Press U to update row in student");
						System.out.println("Press D to delete row in student");
						
						// Choose operation that you want to execute
						System.out.println("Select which operation you want to execute");
						char select = scan.next().charAt(0);
						
						switch(select) 
						{
						// Performing create/insert operation
						case 'C':
							// Taking input from user for insert query
							System.out.print("Enter student ID");
							 sid = scan.nextInt();		// Enter student id
							
							System.out.print("Enter student name");
							 sname = scan.next();		// Enter student name
							
							System.out.print("Enter student age");
							 sage = scan.nextInt();		// Enter student age
							
							// SQL insert query
							String insertQuery = 
							String.format("insert into student(`sid`,`sname`,`sage`) values(%d,'%s',%d)", sid,sname,sage); 
							int noOfRowsCreated = statement.executeUpdate(insertQuery);
							System.out.println("Number of rows affected, " + noOfRowsCreated);
							break;
							
						// Reading table from database
						case 'R':
							// SQL select query
							
							// is ResultSet object is available
							if(resultSet != null) {
								System.out.println("sid\tsname\tsage");
								
								/* check the ResultSet object is available or not
								 * if it is present collect inside the variables and print in the console
								 * if not exit the loop 
								 */
								while(resultSet.next()) {
									int studid = resultSet.getInt("sid");
									String studName = resultSet.getString("sname");
									int studAge = resultSet.getInt("sage");
									System.out.println(studid + "\t" +studName + "\t" + studAge);
								}
							}
							
							break;
							
						// Update the particular block
						case 'U':
							int noOfRowsUpdated;
							// is ResultSet object is available
							if(resultSet != null) {
								
								/* check the ResultSet object is available or not
								 * if it is present collect inside the variables and update the specific block
								 * if not exit the loop 
								 */
								while(resultSet.next()) {
									int studid = resultSet.getInt("sid");
									String studName = resultSet.getString("sname");
									int studAge = resultSet.getInt("sage");
									
									
									// Tell which column you want to update*/
									System.out.println("Enter column you want to change");
									String update = scan.next();
									if(update.equals("sname")) {
										System.out.println("Enter student id");
										sid = scan.nextInt();
										System.out.println("Enter new name");
										String changeSname = scan.next();
										String updateQuery = String.format("update student set %s='%s' where sid=%d", update, changeSname, sid);
										 noOfRowsUpdated = statement.executeUpdate(updateQuery);
										 System.out.println("Number of rows updated, " + noOfRowsUpdated);
										 break;
									}
									if(update.equals("sage")) {
										System.out.println("Enter student id");
										sid = scan.nextInt();
										System.out.println("Enter new age");
										int changeSage = scan.nextInt();
										String updateQuery = String.format("update student set %s=%d where sid=%d", update, changeSage, sid);
										 noOfRowsUpdated = statement.executeUpdate(updateQuery);
										 System.out.println("Number of rows updated, " + noOfRowsUpdated);
										 break;
									}
								}
							}
							break;
							
						case 'D':
							System.out.println("Which row you want to delete");
							int deleteRow = scan.nextInt();
							boolean checkSid = false;
							int studid = 0;
							/* check the ResultSet object is available or not
							 * if ResultSet object is present then collect the sid and delete the specific row
							 * if not exit the loop 
							 */
							while(resultSet.next()) {
								 studid = resultSet.getInt("sid");
	//							 String studName = resultSet.getString("sname");
	//							 int studAge = resultSet.getInt("sage");
								
								
								// Tell which row you want to delete
								if(deleteRow == studid) {
									checkSid = true;
									break;
								}
							}
							if(checkSid) {
								// SQL delete query
								String deleteQuery = String.format("delete from student where sid=%d", studid);
								int noOfRowsDeleted = statement.executeUpdate(deleteQuery);
								System.out.println("Number of rows deleted, " + noOfRowsDeleted);
							}
							break;
						}
					}
				}
			}
		}
		catch(SQLException sql) {
			sql.printStackTrace();
		}
		finally {
			try {
				// Closing the resources
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}

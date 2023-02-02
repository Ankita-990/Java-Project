package date.sql;

public class Test 
{
	public static void main(String[] args) {
		
		java.util.Date uDate = new java.util.Date();
		System.out.println("Util Date :: " + uDate);
		
		long l = uDate.getTime();

		java.sql.Date sqlDate = new java.sql.Date(l);
		System.out.println("SQL Date :: " + sqlDate);
		
	}
}

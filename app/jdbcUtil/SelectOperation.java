package app.jdbcUtil;

import java.util.Scanner;
import app.dynamicInput.*;

public class SelectOperation 
{
	public static void main(String[] args) 
	{
		System.out.println("Which CRUD operation you want to perform");
		Scanner scan = new Scanner(System.in);
		String crud = scan.next();
		
		switch(crud) {
		case "create":
			InsertQuery iq = new InsertQuery();
			iq.insert();
			break;
		case "read":
			SelectQuery sq = new SelectQuery();
			sq.select();
			break;
		case "update":
			UpdateQuery uq = new UpdateQuery();
			uq.update();
			break;
		case "delete":
			DeleteQuery dq = new DeleteQuery();
			dq.delete();
			break;
		}
	}
}

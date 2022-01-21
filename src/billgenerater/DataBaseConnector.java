package billgenerater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

	public static Connection getConnection()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/newspaper","root","");
			System.out.println("Connected");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	

}
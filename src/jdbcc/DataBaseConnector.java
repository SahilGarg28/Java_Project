package jdbcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

	public static Connection getConnection()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/real_java","root","");
			System.out.println("Connected");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	

}

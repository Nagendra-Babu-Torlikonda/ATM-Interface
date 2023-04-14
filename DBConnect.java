package atminterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect
{
	Connection con;
	
	DBConnect() throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myatm", "root", "root");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getCon()
	{
		return con;
	}
	
	
}

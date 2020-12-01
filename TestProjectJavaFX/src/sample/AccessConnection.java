import java.sql.*;

public class AccessConnection {
	public static Connection connection = null;
	
	public Connection getCurrentConnection()
	{
		if (connection!= null)
		{
			return connection;
		}
		else
		{
			String databaseURL = "jdbc:ucanaccess://foodDeliveryDB.accdb";
			
			try {
				connection = DriverManager.getConnection(databaseURL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		return connection;
		}
	}
	
	public void endConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

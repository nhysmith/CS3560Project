package DaeYoungsBackEnd;

import java.sql.*;
public class Driver {
	public void printDriver(Connection c)
	{
		int count = 0;
		try {
			String sql = "SELECT * FROM Driver";		
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(sql);
			rs.last();
			count = rs.getRow();

			System.out.println("Count is: " + count);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int randDriver = (int)(Math.random()*(count)+1);
		System.out.println(randDriver);
		
		try {
			String sql = "SELECT * FROM Driver WHERE driverID = ?";
			PreparedStatement statement = null;			
			statement = c.prepareStatement(sql);
			statement.setInt(1,randDriver);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				String licensePlate = result.getString("licensePlate");
				String carModel = result.getString("carModel");
				System.out.println(licensePlate + " " + carModel + " ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

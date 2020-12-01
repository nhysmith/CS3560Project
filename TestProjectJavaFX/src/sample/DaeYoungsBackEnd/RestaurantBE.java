package DaeYoungsBackEnd;

import java.sql.*;
public class RestaurantBE {
	int restaurantID;
	String restaurantName;
	String restaurantAddress;
	boolean operational;
	

	public void showAllRestaurants(Connection c) {
		String sql = "SELECT * FROM Restaurant";
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				restaurantName = result.getString("restaurantName");	//could create a list or set of Restaurants and save info
				restaurantAddress = result.getString("restaurantAddress");
				System.out.println(restaurantName + " " + restaurantAddress + " ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void searchRestaurant(Connection c, String s){
		String sql = "SELECT * FROM Restaurant WHERE restaurantName like ?";
		
		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);
			statement.setString(1,s);			
			ResultSet result = statement.executeQuery(); //this could be returned instead if we want to see all 
			while (result.next()) {
				restaurantName = result.getString("restaurantName");
				restaurantAddress = result.getString("restaurantAddress");
				System.out.println(restaurantName + " " + restaurantAddress + " ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

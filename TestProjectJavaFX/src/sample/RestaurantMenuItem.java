package sample;

public class RestaurantMenuItem
{
    String itemName;
    String description;
    double price;
    String restaurantID;

    RestaurantMenuItem(String itemName, String description, double price)
    {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }
    
    RestaurantMenuItem(String itemName, String description, double price, String restaurantID)
    {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.restaurantID = restaurantID;
    }
}

package sample;

public class RestaurantMenuItem
{
    String itemName;
    String description;
    double price;
    String restaurantID;
    int itemID;

    RestaurantMenuItem(String itemName, String description, double price)
    {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
    }
   
    RestaurantMenuItem(String itemName, String description, double price, String restaurantID, int itemID)                 
    {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.restaurantID = restaurantID;
        this.itemID = itemID
    }
}

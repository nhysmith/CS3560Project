package sample;

public class Restaurant
{
    String name;
    String address;
    String phoneNumber;
    RestaurantMenuItem[] items;
    String restaurantID;

    Restaurant(String name, String address, String phoneNumber, RestaurantMenuItem[] items)
    {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.items = items.clone();
    }
}

package sample;

public class User
{
    String username;
    String address;
    String phoneNumber;

    Cart cart;

    User(String username, String address, String phoneNumber)
    {
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

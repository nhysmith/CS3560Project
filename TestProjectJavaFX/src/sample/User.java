package sample;

public class User
{
    String email;
    String address;
    String phoneNumber;

    Cart cart;

    User(String email, String address, String phoneNumber)
    {
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

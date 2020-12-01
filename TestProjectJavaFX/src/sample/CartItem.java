package sample;

public class CartItem {
    String itemName;
    int quantity;
    double price;
    double totalPrice;
    int itemID;

    CartItem(String itemName, int quantity, double price)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this. price = price;
        this.totalPrice = quantity*price;
    }

        CartItem(String itemName, int quantity, double price, int itemID)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this. price = price;
        this.totalPrice = quantity*price;
        this.itemID = itemID
    }
}

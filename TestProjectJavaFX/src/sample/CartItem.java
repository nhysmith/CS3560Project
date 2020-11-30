package sample;

public class CartItem {
    String itemName;
    int quantity;
    double price;
    double totalPrice;
    String menuItemID;

    CartItem(String itemName, int quantity, double price)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this. price = price;
        this.totalPrice = quantity*price;
    }
}

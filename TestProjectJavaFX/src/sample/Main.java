package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    //Cart elements
    Cart cart = new Cart();
    Button cartButton = new Button("Cart: " + cart.items.size());

    //Restaurant elements
    Label restaurantName = new Label("");
    Label restaurantAddress = new Label("");
    Label restaurantPhoneNumber = new Label("");

    //VBox menuItemVBox = new VBox(10);
    VBox restaurantVBox;
    Label menuLabel = new Label("Menu: ");

    VBox cartVBox = new VBox(10);

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Dummy Restaurant Menu Arrays
        RestaurantMenuItem[] r1Items = {new RestaurantMenuItem("Cheeseburger", "A hamburger w/ cheese", 4.99),
                new RestaurantMenuItem("Hamburger", "A hamburger w/ lettuce and tomato", 3.99),
                new RestaurantMenuItem("BLT", "A hamburger w/ bacon, lettuce, tomato", 5.99)};

        RestaurantMenuItem[] r2Items = {new RestaurantMenuItem("California Roll", "A roll with avocado", 7.95),
                new RestaurantMenuItem("Rainbow Roll", "A roll with salmon, eel, and tuna", 10.00),
                new RestaurantMenuItem("Salmon Roll", "A roll with salmon", 5.50)};

        RestaurantMenuItem[] r3Items = {new RestaurantMenuItem("Cheese Pizza", "A hamburger w/ cheese", 10.00),
                new RestaurantMenuItem("Pepperoni Pizza", "A hamburger w/ lettuce and tomato", 11.00),
                new RestaurantMenuItem("Hawaiian Pizza", "A hamburger w/ bacon, lettuce, and tomato", 12.00)};

        //Dummy Restaurant Classes
        Restaurant r1 = new Restaurant("Bob's Burgers", "1234 Main Street", "18001234567", r1Items);
        Restaurant r2 = new Restaurant("On a Roll Sushi", "1234 First Ave", "18001234568", r2Items);
        Restaurant r3 = new Restaurant("Take a Pizza My Heart", "1234 Central Blvd", "18001234569", r3Items);

        //Dummy User
        User user = new User("user1234", "101 Binary Ln", "1234567");

        //navBar elements
        Button accountButton = new Button("Account");
        Button homeButton = new Button("GrubDash");
        //Label name = new Label("GrubDash");
        //Label cartLabel = new Label("0");

        //navBar HBox
        HBox navBar = new HBox(30, homeButton, accountButton,cartButton);
        navBar.setAlignment(Pos.CENTER);

        //Initially false so that users have to login first
        navBar.setVisible(false);

        //Home elements
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");

        Button restaurant1 = new Button("Burger Place");
        Button restaurant2 = new Button("Sushi Place");
        Button restaurant3 = new Button("Pizza Place");

        Label restaurantLabel1 = new Label("Bob's Burgers");
        Label restaurantLabel2 = new Label("On a Roll Sushi");
        Label restaurantLabel3 = new Label("Take a Pizza My Heart");

        Label popularLabel = new Label("Popular Restaurants:");

        //Search HBox
        HBox searchHBox = new HBox(10, searchText, searchButton);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.setTranslateY(-100);

        //Search VBox
        VBox searchVBox = new VBox(10);
        searchVBox.setAlignment(Pos.CENTER);

        //Home GridPane
        GridPane homeRestaurantGP = new GridPane();
        homeRestaurantGP.setAlignment(Pos.CENTER);
        homeRestaurantGP.setHgap(10);
        homeRestaurantGP.setVgap(10);

        homeRestaurantGP.add(restaurantLabel1, 0, 0);
        homeRestaurantGP.add(restaurant1, 1, 0);
        homeRestaurantGP.add(restaurantLabel2, 0, 1);
        homeRestaurantGP.add(restaurant2, 1, 1);
        homeRestaurantGP.add(restaurantLabel3, 0, 2);
        homeRestaurantGP.add(restaurant3, 1, 2);

        //Home VBox
        VBox homeVBox = new VBox(5, searchHBox, popularLabel, homeRestaurantGP);
        homeVBox.setAlignment(Pos.CENTER);

        //Login elements
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        Label error1Label = new Label("Username is required!");
        Label error2Label = new Label("Password is required!");
        error1Label.setVisible(false);
        error2Label.setVisible(false);

        TextField usernameText = new TextField();
        PasswordField passwordText = new PasswordField();

        Button submitButton = new Button("Submit");

        //Login GridPane
        GridPane loginGP = new GridPane();
        loginGP.setAlignment(Pos.CENTER);
        loginGP.setHgap(10);
        loginGP.setVgap(10);

        loginGP.add(usernameLabel, 0, 0);
        loginGP.add(usernameText, 1, 0);
        loginGP.add(error1Label, 0, 1);

        loginGP.add(passwordLabel, 0, 2);
        loginGP.add(passwordText, 1, 2);
        loginGP.add(error2Label, 0, 3);

        //Login VBox
        VBox loginVBox = new VBox(10, loginGP, submitButton);
        loginVBox.setAlignment(Pos.CENTER);

        //Menu Item elements
        Label itemNameLabel = new Label();
        Label itemDescriptionLabel = new Label();
        Label itemPriceLabel = new Label();

        Button viewItemButton = new Button("View");

        //Menu Item HBox
        HBox menuItemHBox = new HBox(10,itemNameLabel,itemDescriptionLabel,itemPriceLabel,viewItemButton);
        menuItemHBox.setAlignment(Pos.CENTER);

        //Restaurant VBox
        restaurantVBox = new VBox(10, restaurantName, restaurantAddress, restaurantPhoneNumber, menuLabel, menuItemHBox);
        //VBox restaurantVBox = new VBox(10, restaurantName, restaurantAddress, restaurantPhoneNumber, menuLabel, menuItemVBox);
        restaurantVBox.setAlignment(Pos.CENTER);

        //Profile Elements
        //Label emailProfileLabel = new Label("Email: ");
        Label addressLabel = new Label("Address: ");
        Label usernameProfileLabel = new Label("Username: ");
        Label phoneLabel = new Label("Phone #:");
        Label lastFourLabel = new Label("Credit Card(Last 4):");

        //Label emailProfileData = new Label("email@example.com");
        Label addressData = new Label("1234 Address Street");
        Label usernameProfileData = new Label("FutureCustomer");
        Label phoneData = new Label("1-800-000-0000");
        Label lastFourData = new Label("0000");

        //Button editButton = new Button("Edit");

        //Profile GridPane
        GridPane profileGP = new GridPane();
        profileGP.setAlignment(Pos.CENTER);
        profileGP.setHgap(10);
        profileGP.setVgap(10);

        profileGP.add(usernameProfileLabel, 0, 0);
        profileGP.add(usernameProfileData, 1, 0);
        //profileGP.add(emailProfileLabel, 0, 1);
        //profileGP.add(emailProfileData, 1, 1);

        profileGP.add(phoneLabel, 0, 1);
        profileGP.add(phoneData, 1, 1);
        profileGP.add(addressLabel, 0, 2);
        profileGP.add(addressData, 1, 2);

        profileGP.add(lastFourLabel,0,3);
        profileGP.add(lastFourData, 1, 3);

        //Profile VBox
        Label spacerLabel = new Label(" ");
        spacerLabel.setPadding(new Insets(20));

        //Cart VBox
        cartVBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navBar);
        borderPane.setBottom(spacerLabel);

        //starting screen
        borderPane.setCenter(loginVBox);

        //============================================================================================
        //Button Controls
        //============================================================================================

        //Login Submit Button
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = usernameText.getText();
                String password = passwordText.getText();

                if(validateLogin(username, password, error1Label, error2Label)) {
                    navBar.setVisible(true);
                    borderPane.setCenter(homeVBox);
                }
            }
        });

        //GrubDash Button: Takes User to the main page
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(homeVBox);
                //borderPane.getCenter().setTranslateY(-100);
            }
        });

        //Account: Takes User to Profile
        accountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                borderPane.setCenter(profileGP);
                //borderPane.getCenter().setTranslateY(-75);
            }
        });

        //Cart: Take User to their cart
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setCartLayout();
                borderPane.setCenter(cartVBox);
            }
        });

        //Search Button
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!searchText.getText().isEmpty())
                {
                    createSearchPage(searchVBox);
                    borderPane.setCenter(searchVBox);
                }
            }
        });

        //Select restaurant1
        restaurant1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setRestaurant(r1);
                borderPane.setCenter(restaurantVBox);
               //borderPane.getCenter().setTranslateY(-75);

            }
        });

        //Select restaurant2
        restaurant2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setRestaurant(r2);
                borderPane.setCenter(restaurantVBox);
                //borderPane.getCenter().setTranslateY(-75);

            }
        });

        //Select restaurant3
        restaurant3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setRestaurant(r3);
                borderPane.setCenter(restaurantVBox);
                //borderPane.getCenter().setTranslateY(-75);

            }
        });

        //Scene home = new Scene(restaurantBP, 300, 275);
        primaryStage.setTitle("GrubDash");
        primaryStage.setScene(new Scene(borderPane, 500, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    //============================================================================================
    //Methods
    //============================================================================================

    //Returns true if both the username and password have data
    boolean validateLogin(String username, String password, Label usernameErrorLabel, Label passwordErrorLabel)
    {
        if ((!username.isEmpty()) && (!password.isEmpty()))
        {
            usernameErrorLabel.setVisible(false);
            passwordErrorLabel.setVisible(false);
            return true;
        }

        else if ((username.isEmpty()) && (!password.isEmpty()))
        {
            usernameErrorLabel.setVisible(true);
            passwordErrorLabel.setVisible(false);
            return false;
        }

        else if ((!username.isEmpty()) && (password.isEmpty()))
        {
            usernameErrorLabel.setVisible(false);
            passwordErrorLabel.setVisible(true);
            return false;
        }

        else {
            usernameErrorLabel.setVisible(true);
            passwordErrorLabel.setVisible(true);
            return false;
        }
    }

    public void createSearchPage(VBox vBox)
    {
        vBox.getChildren().clear();
        TextField searchBar = new TextField();
        Button button = new Button("Search");

        HBox hBox = new HBox(10, searchBar, button);

        vBox.getChildren().add(hBox);

    }

    //Add the specific restaurant info (the name, phone #, etc)
    public void setRestaurant(Restaurant restaurant)
    {
        //clear VBox in case there was another restaurant selected
        restaurantVBox.getChildren().clear();

        //Set restaurant name, address, phone number
        restaurantName.setText(restaurant.name);
        restaurantAddress.setText(restaurant.address);
        restaurantPhoneNumber.setText(restaurant.phoneNumber);

        VBox menuItemVBox = new VBox(10);

        //Add each menu item to the VBox
        for (RestaurantMenuItem item : restaurant.items)
        {
            menuItemVBox.getChildren().add(addMenuItem(restaurant, item));
        }

        restaurantVBox = new VBox(10, restaurantName, restaurantAddress, restaurantPhoneNumber, menuLabel, menuItemVBox);
        restaurantVBox.setAlignment(Pos.CENTER);
    }

    //
    public void validateQuantity(int quantity, Button decreaseButton, Button addButton)
    {
        if(quantity >= 1)
        {
            decreaseButton.setDisable(false);
            addButton.setDisable(false);
        }
        else if(quantity == 0)
        {
                decreaseButton.setDisable(true);
                addButton.setDisable(true);
        }
    }

    //Update price label
    public void updatePrice(int quantity, double price, Label priceLabel)
    {
        double updatedPrice = quantity*price;
        priceLabel.setText("$" + String.format("%.2f", updatedPrice));
    }

    //This method creates the HBox for each individual menu item
    public HBox addMenuItem(Restaurant restaurant, RestaurantMenuItem rmi)
    {
        Label itemNameLabel = new Label(rmi.itemName);
        Label itemDescriptionLabel = new Label(rmi.description);
        Label itemPriceLabel = new Label("$" + String.format("%.2f", rmi.price));

        Button viewItemButton = new Button("View");

        //When the View Button is clicked a pop up will appear and the user will be able to add to cart from that window
        viewItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage itemStage = new Stage();
                final int[] itemQuantity = {1};
                Label itemNameLabelPopUp = new Label(rmi.itemName);
                Label itemDescriptionLabelPopUp = new Label(rmi.description);
                Label totalItemPriceLabel = new Label("$" + String.valueOf(rmi.price));
                Label quantityLabel = new Label("Quantity: ");

                TextField quantityData = new TextField(String.valueOf(itemQuantity[0]));
                //Might have to make this a label
                //Need to update price to match textfield if input is typed

                Button increaseButton = new Button("+");
                Button decreaseButton = new Button("-");
                Button addButton = new Button("Add");

                //Handles user input from textfield
                quantityData.textProperty().addListener((observable, oldValue, newValue )->
                {
                    //Disable the add and decrease buttons if textField is blank
                    if(newValue.isEmpty())
                    {
                        decreaseButton.setDisable(true);
                        addButton.setDisable(true);
                    }
                    //Validate numeric input
                    else if(newValue.matches("\\d*"))
                    {
                        validateQuantity(Integer.valueOf(newValue), decreaseButton, addButton);
                        updatePrice(Integer.valueOf(newValue), rmi.price, totalItemPriceLabel);
                        itemQuantity[0] = Integer.valueOf(newValue);
                    }
                    //Disable the add and decrease buttons if the input isn't numeric
                    else {
                        decreaseButton.setDisable(true);
                        addButton.setDisable(true);
                        quantityData.setText("0");
                        updatePrice(0, rmi.price, totalItemPriceLabel);
                        itemQuantity[0] = 0;
                    }
                });

                //Increases the quantity of the item
                increaseButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                       itemQuantity[0]++;

                       if(itemQuantity[0] >0)
                       {
                           decreaseButton.setDisable(false);
                           addButton.setDisable(false);
                       }
                       quantityData.setText(String.valueOf(itemQuantity[0]));

                       updatePrice(itemQuantity[0], rmi.price, totalItemPriceLabel);
                    }
                });

                //Decreases the quantity of item
                decreaseButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        itemQuantity[0]--;

                        if(itemQuantity[0] <=0)
                        {
                            decreaseButton.setDisable(true);
                            addButton.setDisable(true);
                        }
                        quantityData.setText(String.valueOf(itemQuantity[0]));

                        updatePrice(itemQuantity[0], rmi.price, totalItemPriceLabel);
                    }
                });

                //Add Menu Item to Cart
                addButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        addItemToCart(itemQuantity[0], restaurant, rmi);

                        itemStage.close();
                    }
                });

                HBox quantityHBox = new HBox(5, quantityLabel, decreaseButton, quantityData, increaseButton);
                quantityHBox.setAlignment(Pos.CENTER);

                VBox itemVBox = new VBox(10, itemNameLabelPopUp, itemDescriptionLabelPopUp, totalItemPriceLabel, quantityHBox, addButton);
                itemVBox.setAlignment(Pos.CENTER);
                Scene itemScene = new Scene(itemVBox, 350, 300);

                itemStage.setScene(itemScene);
                itemStage.show();
            }
        });

        //Menu Item HBox
        HBox menuItemHBox = new HBox(10,itemNameLabel,itemDescriptionLabel,itemPriceLabel,viewItemButton);
        menuItemHBox.setAlignment(Pos.CENTER);

        return menuItemHBox;
    }

    void addItemToCart(int numItems, Restaurant restaurant, RestaurantMenuItem rmi)
    {
        setCartRestaurant(restaurant);

        cart.numItems += numItems;
        cartButton.setText("Cart: " + cart.numItems);

        //CartItem(String itemName, int quantity, double price)
        CartItem newItem = new CartItem(rmi.itemName, numItems, rmi.price);
        //System.out.println("Add item to arraylist");
        cart.items.add(newItem);
        cart.totalDue += newItem.totalPrice;
    }

    void setCartRestaurant(Restaurant restaurant)
    {
        //If there is something in the cart clear it
        if(cart.restaurant != null)
        {
            if(!cart.restaurant.equals(restaurant))
            {
                cart.items.clear();
                cart.numItems = 0;
                cart.totalDue = 0.0;
            }
            else
                return;
        }

        cart.restaurant = restaurant;
    }

    //Create the cart part when the cart is empty
    public void setEmptyCart()
    {
        cartVBox.getChildren().clear();

        Label numItemsLabel = new Label("You currently have " + cart.numItems + " items in your cart");

        cartVBox.getChildren().add( numItemsLabel);
    }

    //Creates the Cart page
    public void setCartLayout()
    {
        if(cart.items.size() == 0)
        {
            setEmptyCart();
            return;
        }
        cartVBox.getChildren().clear();

        Label numItemsLabel = new Label("You currently have " + cart.numItems + " items in your cart");

        Label restaurantNameLabel = new Label(cart.restaurant.name);
        Label restaurantAddressLabel = new Label(cart.restaurant.address);
        Label restaurantPhoneLabel = new Label(cart.restaurant.phoneNumber);

        Label totalLabel = new Label("Total Due: $" + String.format("%.2f", cart.totalDue));

        Button completeOrderButton = new Button("Complete Order");

        VBox restaurantInfoVBox = new VBox(10, restaurantNameLabel, restaurantAddressLabel, restaurantPhoneLabel);
        restaurantInfoVBox.setAlignment(Pos.CENTER);

        VBox cartItemVBox = new VBox(10);

        //Add each menu item to the VBox
        for (CartItem item : cart.items)
        {
            cartItemVBox.getChildren().add(setCartItem(item));
        }

        cartVBox.getChildren().addAll( numItemsLabel, restaurantInfoVBox, cartItemVBox, totalLabel, completeOrderButton);
    }

    public void deleteItem(CartItem cartItem)
    {
        cart.numItems -= cartItem.quantity;
        cart.totalDue -= cartItem.totalPrice;
        cart.items.remove(cartItem);
        cartButton.setText("Cart: " + cart.numItems);
    }

    public HBox setCartItem(CartItem cartItem)
    {
        Label itemNameLabel = new Label(cartItem.itemName);
        Label itemQuantityLabel = new Label("x" + String.valueOf(cartItem.quantity));
        Label itemPriceLabel = new Label("$" + String.valueOf(cartItem.price));

        Button editItemButton = new Button("Edit");
        Button deleteItemButton = new Button("Delete");

        deleteItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteItem(cartItem);

                setCartLayout();
            }
        });

        //When the Edit Button is clicked a pop up will appear
        editItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final int[] tempQuantity = {cartItem.quantity};
                Stage itemStage = new Stage();
                Label itemNameLabelPopUp = new Label(cartItem.itemName);
                Label totalItemPriceLabel = new Label("$" + cartItem.totalPrice);
                Label quantityLabel = new Label("Quantity: ");

                TextField quantityData = new TextField(String.valueOf(cartItem.quantity));

                Button increaseButton = new Button("+");
                Button decreaseButton = new Button("-");
                Button saveButton = new Button("Save");

                quantityData.textProperty().addListener((observable, oldValue, newValue )->
                {
                    if(newValue.isEmpty())
                    {
                        decreaseButton.setDisable(true);
                        saveButton.setDisable(true);
                    }
                    else if(newValue.matches("\\d*"))
                    {
                        validateQuantity(Integer.valueOf(newValue), decreaseButton, saveButton);
                        updatePrice(Integer.valueOf(newValue), cartItem.price, totalItemPriceLabel);
                        tempQuantity[0] = Integer.valueOf(newValue);
                    }
                    else {
                        decreaseButton.setDisable(true);
                        saveButton.setDisable(true);
                        quantityData.setText("0");
                        updatePrice(0, cartItem.price, totalItemPriceLabel);
                        tempQuantity[0] = 0;
                    }
                });

                increaseButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        tempQuantity[0]++;

                        if(tempQuantity[0] >0)
                        {
                            decreaseButton.setDisable(false);
                            saveButton.setDisable(false);
                        }
                        quantityData.setText(String.valueOf(tempQuantity[0]));

                        double total = tempQuantity[0]*cartItem.price;
                        totalItemPriceLabel.setText("$" + String.format("%.2f", total));

                    }
                });

                decreaseButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        tempQuantity[0]--;

                        if(tempQuantity[0] <=0)
                        {
                            decreaseButton.setDisable(true);
                            saveButton.setDisable(true);
                        }
                        quantityData.setText(String.valueOf(tempQuantity[0]));

                        double total = tempQuantity[0]*cartItem.price;
                        totalItemPriceLabel.setText("$" + String.format("%.2f", total));
                    }
                });

                //Save changes
                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //Subtract current quantity and price
                        cart.totalDue -= cartItem.totalPrice;
                        cart.numItems -= cartItem.quantity;

                        //Update quantity
                        cartItem.quantity = tempQuantity[0];
                        cart.numItems += cartItem.quantity;
                        cartButton.setText("Cart: " + cart.numItems);

                        //Update price
                        cartItem.totalPrice = cartItem.quantity*cartItem.price;
                        cart.totalDue += cartItem.totalPrice;

                        setCartLayout();
                        itemStage.close();
                    }
                });

                HBox quantityHBox = new HBox(5, quantityLabel, decreaseButton, quantityData, increaseButton);
                quantityHBox.setAlignment(Pos.CENTER);

                VBox itemVBox = new VBox(10, itemNameLabelPopUp, totalItemPriceLabel, quantityHBox, saveButton);
                itemVBox.setAlignment(Pos.CENTER);
                Scene itemScene = new Scene(itemVBox, 350, 300);

                itemStage.setScene(itemScene);
                itemStage.show();
            }
        });

        //Menu Item HBox
        HBox cartItemHBox = new HBox(10, itemNameLabel, itemQuantityLabel, itemPriceLabel, editItemButton, deleteItemButton);
        cartItemHBox.setAlignment(Pos.CENTER);

        return cartItemHBox;
    }

}

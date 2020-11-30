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
    //BorderPane
    BorderPane borderPane = new BorderPane();

    //Cart elements
    Cart cart = new Cart();
    Button cartButton = new Button("Cart: " + cart.items.size());

    VBox cartVBox = new VBox(10);

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

    @Override
    public void start(Stage primaryStage) throws Exception{
        //navBar elements
        Button accountButton = new Button("Account");
        Button homeButton = new Button("GrubDash");
        //Label name = new Label("GrubDash");

        //navBar HBox
        HBox navBar = new HBox(30, homeButton, accountButton,cartButton);
        navBar.setAlignment(Pos.CENTER);

        //Initially false so that users have to login first
        navBar.setVisible(false);

        //Login
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        Label error1Label = new Label("Email is required!");
        Label error2Label = new Label("Password is required!");
        error1Label.setVisible(false);
        error2Label.setVisible(false);

        TextField emailText = new TextField();
        PasswordField passwordText = new PasswordField();

        Button signInButton = new Button("Sign in");

        //Login GridPane
        GridPane loginGP = new GridPane();
        loginGP.setAlignment(Pos.CENTER);
        loginGP.setHgap(10);
        loginGP.setVgap(10);

        loginGP.add(emailLabel, 0, 0);
        loginGP.add(emailText, 1, 0);
        loginGP.add(error1Label, 0, 1);

        loginGP.add(passwordLabel, 0, 2);
        loginGP.add(passwordText, 1, 2);
        loginGP.add(error2Label, 0, 3);

        //Login VBox
        VBox loginVBox = new VBox(10, loginGP, signInButton);
        loginVBox.setAlignment(Pos.CENTER);

        //Spacer Label
        Label spacerLabel = new Label(" ");
        spacerLabel.setPadding(new Insets(20));

        //Cart VBox
        cartVBox.setAlignment(Pos.CENTER);

        borderPane.setTop(navBar);
        borderPane.setBottom(spacerLabel);

        //starting screen: User has to login before going to the homepage
        borderPane.setCenter(loginVBox);

        //============================================================================================
        //Button Controls
        //============================================================================================

        //Sign in Button
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailText.getText();
                String password = passwordText.getText();

                if(validateLogin(email, password, error1Label, error2Label)) {
                    navBar.setVisible(true);
                   createHomePage();
                }
            }
        });

        //GrubDash Button: Takes User to the main page
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createHomePage();
            }
        });

        //Account: Takes User to Profile
        accountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createProfilePage();
            }
        });

        //Cart: Take User to their cart
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createCartPage();
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
    boolean validateLogin(String email, String password, Label emailErrorLabel, Label passwordErrorLabel)
    {
        if ((!email.isEmpty()) && (!password.isEmpty()))
        {
            //============================================
            //This is where authentication needs to happen
            //============================================
            emailErrorLabel.setVisible(false);
            passwordErrorLabel.setVisible(false);
            return true;
        }

        else if ((email.isEmpty()) && (!password.isEmpty()))
        {
            emailErrorLabel.setVisible(true);
            passwordErrorLabel.setVisible(false);
            return false;
        }

        else if ((!email.isEmpty()) && (password.isEmpty()))
        {
            emailErrorLabel.setVisible(false);
            passwordErrorLabel.setVisible(true);
            return false;
        }

        else {
            emailErrorLabel.setVisible(true);
            passwordErrorLabel.setVisible(true);
            return false;
        }
    }

    //Creates a homepage with search bar and a popular restaurants section
    public void createHomePage()
    {
        VBox homeVBox = new VBox(10, createSearchBar(), createPopularRestaurantVBox());
        homeVBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(homeVBox);
    }

    //Creates a profile page where user info is stored and orders are displayed
    public void createProfilePage()
    {
        Label emailProfileLabel = new Label("Email: ");
        Label addressLabel = new Label("Address: ");
        Label phoneLabel = new Label("Phone #:");
        Label lastFourLabel = new Label("Credit Card(Last 4):");

        Label emailProfileData = new Label("email@example.com");
        Label addressData = new Label("1234 Address Street");
        Label phoneData = new Label("1-800-000-0000");
        Label lastFourData = new Label("0000");

        Label ordersLabel = new Label("Your Orders: ");
        //==============================================
        //How are we handling orders after checkout?
        //==============================================

        GridPane profileGP = new GridPane();
        profileGP.setAlignment(Pos.CENTER);
        profileGP.setHgap(10);
        profileGP.setVgap(10);

        profileGP.add(emailProfileLabel, 0, 0);
        profileGP.add(emailProfileData, 1, 0);

        profileGP.add(phoneLabel, 0, 1);
        profileGP.add(phoneData, 1, 1);
        profileGP.add(addressLabel, 0, 2);
        profileGP.add(addressData, 1, 2);

        profileGP.add(lastFourLabel,0,3);
        profileGP.add(lastFourData, 1, 3);

        VBox profileVBox = new VBox(10, profileGP, ordersLabel);
        profileVBox.setAlignment(Pos.CENTER);

        borderPane.setCenter(profileVBox);
    }

    //Creates search bar, error label, and handles search button
    public VBox createSearchBar()
    {
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");
        Label searchErrorLabel = new Label("Must contain text");
        searchErrorLabel.setVisible(false);

        HBox hBox = new HBox(10, searchText, searchButton);
        hBox.setAlignment(Pos.CENTER);

        //Search Button
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!searchText.getText().isEmpty())
                {
                    //========================
                    //searchText.getText() needs to be queried
                    //========================
                    createSearchPage();
                    searchErrorLabel.setVisible(false);
                }
                else
                {
                    searchErrorLabel.setVisible(true);
                }
            }
        });

        VBox vBox = new VBox(10,hBox,searchErrorLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateY(-90);
        return vBox;
    }

    //Create the search page after the search button is pressed
    public void createSearchPage()
    {
        //=========================================
        //Probably needs a search results parameter
        //=========================================
        VBox searchVBox = new VBox(10,createSearchBar(), createPopularRestaurantVBox());
        searchVBox.setAlignment(Pos.CENTER);

        //=============================
        //For each search result createRestaurantHBox
        //==============================

        borderPane.setCenter(searchVBox);
    }

    //Creates a label w/ the restaurant's name and a button that leads to that restaurant's page/menu
    public HBox createRestaurantHBox(Restaurant restaurant)
    {
        Label rNameLabel = new Label(restaurant.name);
        Button rNameButton = new Button(restaurant.name);

        rNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createRestaurantPage(restaurant);
            }
        });
        HBox hBox = new HBox(10, rNameLabel, rNameButton);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    //Creates a Restaurant Page that lists restaurant's info and menu
    public void createRestaurantPage(Restaurant restaurant)
    {
        //Set restaurant name, address, phone number
        Label restaurantName = new Label(restaurant.name);
        Label restaurantAddress = new Label(restaurant.address);
        Label restaurantPhoneNumber = new Label(restaurant.phoneNumber);

        Label menuLabel = new Label("Menu: ");

        VBox menuItemVBox = new VBox(10);

        //Add each menu item to the VBox
        for (RestaurantMenuItem item : restaurant.items)
        {
            menuItemVBox.getChildren().add(addMenuItem(restaurant, item));
        }

        VBox restaurantVBox = new VBox(10, restaurantName, restaurantAddress, restaurantPhoneNumber, menuLabel, menuItemVBox);
        restaurantVBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(restaurantVBox);
    }

    //Conditionally enables/disables the decrease and add button based on quantity
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

    //Creates a VBox with 3 restaurants
    public VBox createPopularRestaurantVBox()
    {
        Label popularLabel = new Label("Popular Restaurants:");

       /* Button r1Button = new Button(r1.name);
        Button r2Button = new Button(r2.name);
        Button r3Button = new Button(r3.name);

        Label r1Label = new Label(r1.name);
        Label r2Label = new Label(r2.name);
        Label r3Label = new Label(r3.name);

        GridPane restaurantGP = new GridPane();
        restaurantGP.setAlignment(Pos.CENTER);
        restaurantGP.setHgap(10);
        restaurantGP.setVgap(10);

        restaurantGP.add(r1Label, 0, 0);
        restaurantGP.add(r1Button, 1, 0);
        restaurantGP.add(r2Label, 0, 1);
        restaurantGP.add(r2Button, 1, 1);
        restaurantGP.add(r3Label, 0, 2);
        restaurantGP.add(r3Button, 1, 2);

        //Select restaurant1
       r1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createRestaurantPage(r1);
                //borderPane.getCenter().setTranslateY(-75);

            }
        });

        //Select restaurant2
        r2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createRestaurantPage(r2);
            }
        });

        //Select restaurant3
        r3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createRestaurantPage(r3);
            }
        });*/

        VBox restaurantVBox = new VBox(10, popularLabel, createRestaurantHBox(r1), createRestaurantHBox(r2), createRestaurantHBox(r3));
        restaurantVBox.setAlignment(Pos.CENTER);

        return restaurantVBox;
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
        Label itemPriceLabel = new Label("$" + String.format("%.2f", rmi.price));

        Button viewItemButton = new Button("View");

        //When the View Button is clicked a pop up will appear and the user will be able to add to cart from that window
        viewItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createViewPopup(restaurant, rmi);
            }
        });

        //Menu Item HBox
        HBox menuItemHBox = new HBox(10,itemNameLabel,itemPriceLabel,viewItemButton);
        menuItemHBox.setAlignment(Pos.CENTER);

        return menuItemHBox;
    }

    //Adds items to cart's arraylist of restaurant menu items, updates number of items and total
    void addItemToCart(int numItems, Restaurant restaurant, RestaurantMenuItem rmi)
    {
        setCartRestaurant(restaurant);

        cart.numItems += numItems;
        cartButton.setText("Cart: " + cart.numItems);

        CartItem newItem = new CartItem(rmi.itemName, numItems, rmi.price);

        cart.items.add(newItem);
        cart.totalDue += newItem.totalPrice;
    }

    //This ensures that the user can only place an order from one restaurant at a time
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

    //Create the cart page when the cart is empty
    public void createEmptyCartPage()
    {
        cartVBox.getChildren().clear();

        Label numItemsLabel = new Label("You currently have " + cart.numItems + " items in your cart");

        cartVBox.getChildren().add( numItemsLabel);
        borderPane.setCenter(cartVBox);
    }

    //Create the cart page when the order has been completed
    public void createCompleteOrderPage()
    {
        cart = new Cart();
        cartButton.setText("Cart: " + cart.numItems);
        cartVBox.getChildren().clear();

        Label driverLabel = new Label("Your driver is Driver");

        cartVBox.getChildren().add(driverLabel);
    }

    //Creates the Cart page
    public void createCartPage()
    {
        if(cart.items.size() == 0)
        {
            createEmptyCartPage();
            return;
        }
        cartVBox.getChildren().clear();

        Label numItemsLabel = new Label("You currently have " + cart.numItems + " items in your cart");

        Label restaurantNameLabel = new Label(cart.restaurant.name);
        Label restaurantAddressLabel = new Label(cart.restaurant.address);
        Label restaurantPhoneLabel = new Label(cart.restaurant.phoneNumber);

        Label orderLabel = new Label("Your Order:");

        Label totalLabel = new Label("Total Due: $" + String.format("%.2f", cart.totalDue));

        Button checkoutButton = new Button("Continue to Checkout");
        checkoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createPaymentPopup();
            }
        });

        VBox restaurantInfoVBox = new VBox(10, restaurantNameLabel, restaurantAddressLabel, restaurantPhoneLabel);
        restaurantInfoVBox.setAlignment(Pos.CENTER);

        VBox cartItemVBox = new VBox(10);

        //Add each menu item to the VBox
        for (CartItem item : cart.items)
        {
            cartItemVBox.getChildren().add(createCartItemHBox(item));
        }

        cartVBox.getChildren().addAll( numItemsLabel, restaurantInfoVBox, orderLabel, cartItemVBox, totalLabel, checkoutButton);

        borderPane.setCenter(cartVBox);
    }

    //Deletes item from cart, updates total price and number of items
    public void deleteItem(CartItem cartItem)
    {
        cart.numItems -= cartItem.quantity;
        cart.totalDue -= cartItem.totalPrice;
        cart.items.remove(cartItem);
        cartButton.setText("Cart: " + cart.numItems);
    }

    //Create HBox for each cart item
    public HBox createCartItemHBox(CartItem cartItem)
    {
        Label itemNameLabel = new Label(cartItem.itemName);
        Label itemQuantityLabel = new Label("x" + String.valueOf(cartItem.quantity));
        Label itemPriceLabel = new Label("$" + String.format("%.2f", cartItem.price));

        Button editItemButton = new Button("Edit");
        Button deleteItemButton = new Button("Delete");

        deleteItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteItem(cartItem);

                createCartPage();
            }
        });

        //When the Edit Button is clicked a pop up will appear
        editItemButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createEditPopup(cartItem);
            }
        });

        //Menu Item HBox
        HBox cartItemHBox = new HBox(10, itemNameLabel, itemQuantityLabel, itemPriceLabel, editItemButton, deleteItemButton);
        cartItemHBox.setAlignment(Pos.CENTER);

        return cartItemHBox;
    }

    //============================================================================================
    //Popup Windows
    //============================================================================================

    //Creates the view item page, users select the quantity of the item and add it to cart
    public void createViewPopup(Restaurant restaurant, RestaurantMenuItem rmi)
    {
        Stage itemStage = new Stage();
        final int[] itemQuantity = {1};
        Label itemNameLabelPopUp = new Label(rmi.itemName);
        Label itemDescriptionLabelPopUp = new Label(rmi.description);
        Label totalItemPriceLabel = new Label("$" + String.format("%.2f", rmi.price));
        Label quantityLabel = new Label("Quantity: ");

        TextField quantityData = new TextField(String.valueOf(itemQuantity[0]));

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

    //Creates the edit item page, users can edit the quantity of the item they've added to the cart
    public void createEditPopup(CartItem cartItem)
    {
        final int[] tempQuantity = {cartItem.quantity};
        Stage itemStage = new Stage();
        Label itemNameLabelPopUp = new Label(cartItem.itemName);
        Label totalItemPriceLabel = new Label("$" + String.format("%.2f", cartItem.totalPrice));
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

                createCartPage();
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

    //Create the Payment Confirmation Popup
    public void createPaymentPopup()
    {
        Stage itemStage = new Stage();

        Label totalLabel = new Label("Your total is: $" + cart.totalDue);
        Label cvvLabel = new Label("CVV: ");
        TextField cvvText = new TextField();

        HBox cvvHBox = new HBox(10, cvvLabel, cvvText);
        cvvHBox.setAlignment(Pos.CENTER);

        Button orderButton = new Button("Complete Order");
        orderButton.setDisable(true);

        cvvText.textProperty().addListener((observable, oldValue, newValue )->
        {
            if(newValue.isEmpty())
            {
                orderButton.setDisable(true);
            }
            //cvv must be 3 digits
            else if(newValue.matches("\\d\\d\\d"))
            {
                orderButton.setDisable(false);
                //System.out.println("Order is complete");
            }
            else {
                orderButton.setDisable(true);
                //cvvText.setText("");
            }
        });

        //Completes the order
        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //==================================
                //Order needs to be added to profile
                //=================================
                createCompleteOrderPage();
                itemStage.close();
            }
        });

        VBox checkoutVBox = new VBox(10, totalLabel, cvvHBox, orderButton);
        checkoutVBox.setAlignment(Pos.CENTER);
        Scene itemScene = new Scene(checkoutVBox, 350, 300);

        itemStage.setScene(itemScene);
        itemStage.show();
    }
}

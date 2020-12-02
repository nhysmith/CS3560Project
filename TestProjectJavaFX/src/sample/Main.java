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

    //Cart
    Cart cart = new Cart();
    VBox cartVBox = new VBox(10);

    //Nav Bar Buttons
    Button cartButton = new Button("Cart: " + cart.items.size());
    Button accountButton = new Button("Account");
    Button homeButton = new Button("GrubDash");
    Button signOutButton = new Button("Sign Out");

    Label nameLabel = new Label("Welcome to GrubDash");

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
        //nameLabel styling
        nameLabel.setId("nameLabel");

        //Spacer Label
        Label spacerLabel = new Label(" ");
        spacerLabel.setPadding(new Insets(20));

        //Cart VBox
        cartVBox.setAlignment(Pos.CENTER);

        VBox labelVBox = new VBox(nameLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setId("labelVBox");

        borderPane.setTop(labelVBox);
        borderPane.setBottom(spacerLabel);

        //starting screen: User has to login before going to the homepage
        //borderPane.setCenter(loginVBox);
        createSignInPage();

        //============================================================================================
        //Button Controls
        //============================================================================================

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

        //Sign out : Take User to the sign in page
        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearCart();
                borderPane.setTop(labelVBox);
                createSignInPage();
            }
        });


        Scene homeScene = new Scene(borderPane, 500, 475);
        homeScene.getStylesheets().add("style.css");
        primaryStage.setTitle("GrubDash");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //============================================================================================
    //Methods
    //============================================================================================

    //
    boolean validateForm(String[] data, Label[] labels)
    {
        /*if(data.length != labels.length)
        {
            System.out.println("Error");
            //return false;
        }*/
        boolean isFormValid = true;
        for (int i = 0; i < data.length; i++)
        {
            if(data[i].isEmpty())
            {
                isFormValid = false;
                labels[i].setVisible(true);
            }
            else
            {
                labels[i].setVisible(false);
            }
        }
        return isFormValid;
    }

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

    //Create NavBar
    public HBox createNavBar()
    {
        HBox navBar = new HBox(30, homeButton, accountButton,cartButton, signOutButton);

        cartButton.setId("navButton");
        accountButton.setId("navButton");
        homeButton.setId("navButton");
        signOutButton.setId("navButton");
        navBar.setId("navBar");

        navBar.setAlignment(Pos.CENTER);

        return navBar;

    }

    //Create Sign In Page
    public void createSignInPage()
    {
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        Label error1Label = new Label("Required field!");
        Label error2Label = new Label("Required field!");
        error1Label.setVisible(false);
        error1Label.setId("errorLabel");
        error2Label.setVisible(false);
        error2Label.setId("errorLabel");

        TextField emailText = new TextField();
        PasswordField passwordText = new PasswordField();

        Button signUpButton = new Button("Sign Up");
        Button signInButton = new Button("Sign In");

        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createSignUpPopup();
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailText.getText();
                String password = passwordText.getText();

                if(validateLogin(email, password, error1Label, error2Label)) {
                    borderPane.setTop(createNavBar());
                    createHomePage();
                }
            }
        });

        HBox buttonHBox = new HBox(10, signUpButton, signInButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setTranslateX(-12);

        //Login GridPane
        GridPane loginGP = new GridPane();
        loginGP.setAlignment(Pos.CENTER);
        loginGP.setHgap(10);
        loginGP.setVgap(10);

        loginGP.add(emailLabel, 0, 0);
        loginGP.add(emailText, 1, 0);
        loginGP.add(error1Label, 2, 0);

        loginGP.add(passwordLabel, 0, 1);
        loginGP.add(passwordText, 1, 1);
        loginGP.add(error2Label, 2, 1);

        //Login VBox
        VBox signInVBox = new VBox(10, loginGP, buttonHBox);
        signInVBox.setAlignment(Pos.CENTER);

        borderPane.setCenter(signInVBox);
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
        Label searchErrorLabel = new Label("Search field can't be blank");
        searchErrorLabel.setVisible(false);
        searchErrorLabel.setId("errorLabel");

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
        vBox.setTranslateY(-75);
        return vBox;
    }

    //Create the search page after the search button is pressed
    public void createSearchPage()
    {
        //=========================================
        //Probably needs a search results parameter
        //=========================================
        //VBox searchVBox = new VBox(10,createSearchBar(), createPopularRestaurantVBox());

        //An example of what a search result will look like
        VBox searchVBox = new VBox(10,createSearchBar(), createRestaurantHBox(r1), createPopularRestaurantVBox());
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
        rNameLabel.setId("searchResult");
        Button rNameButton = new Button("Menu");

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
        restaurantName.setId("rName");
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
        popularLabel.setId("titleLabel");

        Button r1Button = new Button("Menu");
        Button r2Button = new Button("Menu");
        Button r3Button = new Button("Menu");

        Label r1Label = new Label(r1.name);
        r1Label.setId("popRest");
        Label r2Label = new Label(r2.name);
        r2Label.setId("popRest");
        Label r3Label = new Label(r3.name);
        r3Label.setId("popRest");

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
        });

        //VBox restaurantVBox = new VBox(10, popularLabel, createRestaurantHBox(r1), createRestaurantHBox(r2), createRestaurantHBox(r3));
        VBox restaurantVBox = new VBox(10, popularLabel, restaurantGP);
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
        itemNameLabel.setId("itemName");
        Label itemPriceLabel = new Label("$" + String.format("%.2f", rmi.price));

        Button viewItemButton = new Button("View");
        viewItemButton.setId("viewButton");

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

    //Creates a new cart object and updates cart button

    public void clearCart()
    {
        cart = new Cart();
        cartButton.setText("Cart: " + cart.numItems);
        cartVBox.getChildren().clear();
    }

    //Create the cart page when the order has been completed
    public void createCompleteOrderPage()
    {
        clearCart();
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
        restaurantNameLabel.setId("rName");
        Label restaurantAddressLabel = new Label(cart.restaurant.address);
        Label restaurantPhoneLabel = new Label(cart.restaurant.phoneNumber);

        Label orderLabel = new Label("Your Order:");

        Label totalLabel = new Label("Total Due: $" + String.format("%.2f", cart.totalDue));

        Button checkoutButton = new Button("Checkout");
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
        Label itemPriceLabel = new Label("($" + String.format("%.2f", cartItem.price) + ")");
        Label itemTotalPriceLabel = new Label("$" + String.format("%.2f", cartItem.totalPrice));

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
        HBox cartItemHBox = new HBox(10, itemNameLabel, itemQuantityLabel, itemPriceLabel, itemTotalPriceLabel, editItemButton, deleteItemButton);
        cartItemHBox.setAlignment(Pos.CENTER);

        return cartItemHBox;
    }

    //============================================================================================
    //Popup Windows
    //============================================================================================

    //Create Sign Up Popup
    public void createSignUpPopup()
    {

        Label signUpLabel = new Label("Sign Up");
        signUpLabel.setId("nameLabel");

        //Page 1
        Label emailLabel = new Label("Email: ");
        Label passwordLabel = new Label("Password: ");
        Label phoneLabel = new Label("Phone#: ");
        Label addressLabel = new Label("Address: ");

        Label error1Label = new Label("Required field!");
        Label error2Label = new Label("Required field!");
        Label error3Label = new Label("Required field!");
        Label error4Label = new Label("Required field!");

        //Page 2
        Label creditCardLabel = new Label("Credit Card: " );
        Label expirationDateLabel = new Label("Expiration Date: ");
        Label cardHolderLabel = new Label("Cardholder: ");
        Label cvvLabel = new Label("CVV: ");
        Label cardAddressLabel = new Label("Billing Address: ");
        Label cityLabel = new Label("City: ");
        Label stateLabel = new Label("State: ");
        Label zipLabel = new Label("Zip: ");


        Label error5Label = new Label("Required field!");
        Label error6Label = new Label("Required field!");
        Label error7Label = new Label("Required field!");
        Label error8Label = new Label("Required field!");
        Label error9Label = new Label("Required field!");
        Label error10Label = new Label("Required field!");
        Label error11Label = new Label("Required field!");
        Label error12Label = new Label("Required field!");

        Label[] labels1 = new Label[]{error1Label, error2Label, error3Label, error4Label};

        Label[] labels2 = new Label[]{error5Label, error6Label, error7Label, error8Label, error9Label, error10Label, error11Label, error12Label};

        for (Label label : labels1)
        {
            label.setVisible(false);
            label.setId("errorLabel");
        }

        for (Label label : labels2)
        {
            label.setVisible(false);
            label.setId("errorLabel");
        }

        TextField emailText = new TextField();
        PasswordField passwordText = new PasswordField();
        TextField phoneText = new TextField();
        TextField addressText = new TextField();

        TextField creditCardText = new TextField();
        TextField expirationDateText = new TextField();
        TextField cardHolderText = new TextField();
        TextField cvvText = new TextField();

        TextField cardAddressText = new TextField();
        TextField cityText = new TextField();
        TextField stateText = new TextField();
        TextField zipText = new TextField();

        //Sign up GridPane
        GridPane signUpGP = new GridPane();
        signUpGP.setAlignment(Pos.CENTER);
        signUpGP.setHgap(10);
        signUpGP.setVgap(10);

        signUpGP.add(emailLabel, 0, 0);
        signUpGP.add(emailText, 1, 0);
        signUpGP.add(error1Label, 2, 0);

        signUpGP.add(passwordLabel, 0, 1);
        signUpGP.add(passwordText, 1, 1);
        signUpGP.add(error2Label, 2, 1);

        signUpGP.add(phoneLabel, 0, 2);
        signUpGP.add(phoneText, 1, 2);
        signUpGP.add(error3Label, 2, 2);

        signUpGP.add(addressLabel, 0, 3);
        signUpGP.add(addressText, 1, 3);
        signUpGP.add(error4Label, 2, 3);

        GridPane cardInfoGP = new GridPane();
        cardInfoGP.setAlignment(Pos.CENTER);
        cardInfoGP.setHgap(10);
        cardInfoGP.setVgap(10);

        cardInfoGP.add(creditCardLabel, 0, 0);
        cardInfoGP.add(creditCardText, 1, 0);
        cardInfoGP.add(error5Label, 2, 0);

        cardInfoGP.add(expirationDateLabel, 0, 1);
        cardInfoGP.add(expirationDateText, 1, 1);
        cardInfoGP.add(error6Label, 2, 1);

        cardInfoGP.add(cardHolderLabel, 0, 2);
        cardInfoGP.add(cardHolderText, 1, 2);
        cardInfoGP.add(error7Label, 2, 2);

        cardInfoGP.add(cvvLabel, 0, 3);
        cardInfoGP.add(cvvText, 1, 3);
        cardInfoGP.add(error8Label, 2, 3);

        cardInfoGP.add(cardAddressLabel, 0, 4);
        cardInfoGP.add(cardAddressText, 1, 4);
        cardInfoGP.add(error9Label, 2, 4);

        cardInfoGP.add(cityLabel, 0, 5);
        cardInfoGP.add(cityText, 1, 5);
        cardInfoGP.add(error10Label, 2, 5);

        cardInfoGP.add(stateLabel, 0, 6);
        cardInfoGP.add(stateText, 1, 6);
        cardInfoGP.add(error11Label, 2, 6);

        cardInfoGP.add(zipLabel, 0, 7);
        cardInfoGP.add(zipText, 1, 7);
        cardInfoGP.add(error12Label, 2, 7);

        //leads to credit card info
        Button continueButton = new Button("Continue");

        //leads to home
        Button submitButton = new Button("Submit");

        //Sign Up VBox
        VBox signUpVBox = new VBox(10, signUpGP, continueButton);
        signUpVBox.setAlignment(Pos.CENTER);

        VBox labelVBox = new VBox(signUpLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setId("labelVBox");

        BorderPane bp = new BorderPane();
        bp.setTop(labelVBox);
        bp.setCenter(signUpVBox);

        Stage signUpStage = new Stage();

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String[] data = new String[]{emailText.getText(), passwordText.getText(), phoneText.getText(), addressText.getText()};
                if(validateForm(data, labels1)) {
                    signUpVBox.getChildren().clear();
                    signUpVBox.getChildren().addAll(cardInfoGP, submitButton);
                }
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { //cardID, expirationDate, cardholderName, cvvNum, address, city, state, zip
                String[] data = new String[]{creditCardText.getText(), expirationDateText.getText(), cardHolderText.getText(), cvvText.getText(), cardAddressText.getText(), cityText.getText(), stateText.getText(), zipText.getText()};
                //System.out.println("Num labels: " + data.length);
                if(validateForm(data, labels2)) {
                    borderPane.setTop(createNavBar());
                    createHomePage();
                    signUpStage.close();
                }
            }
        });

        Scene signUpScene = new Scene(bp, 400, 400);

        signUpScene.getStylesheets().add("style.css");
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }

    //Creates the view item page, users select the quantity of the item and add it to cart
    public void createViewPopup(Restaurant restaurant, RestaurantMenuItem rmi)
    {
        Label restaurantNameLabel = new Label(restaurant.name);
        restaurantNameLabel.setId("nameLabel");

        VBox labelVBox = new VBox(restaurantNameLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setId("labelVBox");

        Stage viewItemStage = new Stage();
        final int[] itemQuantity = {1};
        Label itemNameLabelPopUp = new Label(rmi.itemName);
        itemNameLabelPopUp.setId("itemName");
        Label itemDescriptionLabelPopUp = new Label(rmi.description);
        Label totalItemPriceLabel = new Label("$" + String.format("%.2f", rmi.price));
        Label quantityLabel = new Label("Quantity: ");

        TextField quantityData = new TextField(String.valueOf(itemQuantity[0]));

        Button increaseButton = new Button("+");
        increaseButton.setId("qButton");
        Button decreaseButton = new Button("-");
        decreaseButton.setId("qButton");
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

                viewItemStage.close();
            }
        });

        HBox quantityHBox = new HBox(5, quantityLabel, decreaseButton, quantityData, increaseButton);
        quantityHBox.setAlignment(Pos.CENTER);

        VBox itemVBox = new VBox(10, itemNameLabelPopUp, itemDescriptionLabelPopUp, totalItemPriceLabel, quantityHBox, addButton);
        itemVBox.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();
        bp.setTop(labelVBox);
        bp.setCenter(itemVBox);

        Scene itemScene = new Scene(bp, 350, 300);

        itemScene.getStylesheets().add("style.css");
        viewItemStage.setScene(itemScene);
        viewItemStage.show();
    }

    //Creates the edit item page, users can edit the quantity of the item they've added to the cart
    public void createEditPopup(CartItem cartItem)
    {
        Label editNameLabel = new Label("Edit");
        editNameLabel.setId("nameLabel");

        VBox labelVBox = new VBox(editNameLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setId("labelVBox");

        final int[] tempQuantity = {cartItem.quantity};
        Stage editItemStage = new Stage();
        Label itemNameLabelPopUp = new Label(cartItem.itemName);
        itemNameLabelPopUp.setId("itemName");
        Label totalItemPriceLabel = new Label("$" + String.format("%.2f", cartItem.totalPrice));
        Label quantityLabel = new Label("Quantity: ");

        TextField quantityData = new TextField(String.valueOf(cartItem.quantity));

        Button increaseButton = new Button("+");
        increaseButton.setId("qButton");
        Button decreaseButton = new Button("-");
        decreaseButton.setId("qButton");
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
                editItemStage.close();
            }
        });

        HBox quantityHBox = new HBox(5, quantityLabel, decreaseButton, quantityData, increaseButton);
        quantityHBox.setAlignment(Pos.CENTER);

        VBox itemVBox = new VBox(10, itemNameLabelPopUp, totalItemPriceLabel, quantityHBox, saveButton);
        itemVBox.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();
        bp.setTop(labelVBox);
        bp.setCenter(itemVBox);

        Scene itemScene = new Scene(bp, 350, 300);

        itemScene.getStylesheets().add("style.css");
        editItemStage.setScene(itemScene);
        editItemStage.show();
    }

    //Create the Payment Confirmation Popup
    public void createPaymentPopup()
    {
        Label checkoutNameLabel = new Label("Checkout");
        checkoutNameLabel.setId("nameLabel");

        VBox labelVBox = new VBox(checkoutNameLabel);
        labelVBox.setAlignment(Pos.CENTER);
        labelVBox.setId("labelVBox");

        Stage paymentStage = new Stage();

        Label customerNameLabel = new Label("Name: ");
        Label creditCard = new Label("Credit Card: ");
        Label totalLabel = new Label("Your total is: $" + String.format("%.2f", cart.totalDue));
        Label cvvLabel = new Label("CVV: ");
        TextField cvvText = new TextField();

        HBox cvvHBox = new HBox(10, cvvLabel, cvvText);
        cvvHBox.setAlignment(Pos.CENTER);

        Button orderButton = new Button("Place Order");
        orderButton.setId("orderButton");
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
                paymentStage.close();
            }
        });

        VBox checkoutVBox = new VBox(10, customerNameLabel, creditCard, totalLabel, cvvHBox, orderButton);
        checkoutVBox.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();
        bp.setTop(labelVBox);
        bp.setCenter(checkoutVBox);

        Scene itemScene = new Scene(bp, 350, 300);

        itemScene.getStylesheets().add("style.css");
        paymentStage.setScene(itemScene);
        paymentStage.show();
    }
}

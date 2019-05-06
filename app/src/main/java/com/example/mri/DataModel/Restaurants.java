package com.example.mri.DataModel;

public class Restaurants {
    private String RestaurantName,Image,Location;

    public static User currentUser;

    public static final String INTENT_RESTURANT_ID = "DetailId";

    public static final String INTENT_MENU_ID = "menuId";

    public Restaurants() {
    }

    public Restaurants(String restaurantName, String image, String location) {
        RestaurantName = restaurantName;
        Image = image;
        Location = location;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}

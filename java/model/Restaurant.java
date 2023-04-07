package model;

import java.util.ArrayList;

public class Restaurant {

    private int restaurantID;
    private String imageReference;
    private String restaurantName;
    private ArrayList<RCategory> categories;
    private int streetNum;
    private String streetName;
    private int postcode;
    private String state;
    private String suburb;
    private String country;
    private Boolean activate;
    private long abn;
    private String accountName;
    private int bsb;
    private int accountNum;
    private MenuManagement menuManagement = new MenuManagement();

    public Restaurant(){}

    // For DBManager to use to fill in info + categories and passed to the controller fo use.
    public Restaurant(int restaurantID, String imageReference, String restaurantName,
                      ArrayList<RCategory> categories, int streetNum, String streetName,
                      int postcode, String state, String suburb, String country,
                      Boolean activate, long abn, String accountName,
                      int bsb, int accountNum) {
        this.restaurantID = restaurantID;
        this.imageReference = imageReference;
        this.categories = categories;
        this.restaurantName = restaurantName;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activate = activate;
        this.abn = abn;
        this.accountName = accountName;
        this.bsb = bsb;
        this.accountNum = accountNum;
    }

    // For creating restaurants where controller uses this to pass to the DBManager for record creation
    // Initially it has no categories
    public Restaurant(String imageReference, String restaurantName,
                      int streetNum, String streetName, int postcode, String state,
                      String suburb, String country, Boolean activate, long abn, String accountName,
                      int bsb, int accountNum) {
        this.restaurantID = -1;
        this.imageReference = imageReference;
        this.restaurantName = restaurantName;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activate = activate;
        this.abn = abn;
        this.accountName = accountName;
        this.bsb = bsb;
        this.accountNum = accountNum;
    }

    // For Update w/ ID being passed in for check
    public Restaurant(int restaurantID, String imageReference, String restaurantName,
                      int streetNum, String streetName, int postcode, String state,
                      String suburb, String country, Boolean activate, long abn, String accountName,
                      int bsb, int accountNum) {
        this.restaurantID = restaurantID;
        this.imageReference = imageReference;
        this.restaurantName = restaurantName;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activate = activate;
        this.abn = abn;
        this.accountName = accountName;
        this.bsb = bsb;
        this.accountNum = accountNum;
    }


    // To be used only when viewing list of restaurants under a particular category, no concern for other information
    public Restaurant(int restaurantID, String restaurantName) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.imageReference = "";
        this.categories = null;
        this.streetNum = -1;
        this.streetName = "";
        this.postcode = -1;
        this.state = "";
        this.suburb = "";
        this.country = "";
        this.activate = false;
        this.abn = -1;
        this.accountName = "";
        this.bsb = -1;
        this.accountNum = -1;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public ArrayList<RCategory> getCategories() {
        return categories;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public long getAbn() {
        return abn;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBsb() {
        return bsb;
    }

    public void setBsb(int bsb) {
        this.bsb = bsb;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public MenuManagement GetMenuManagement() {
        if(menuManagement == null) {
           menuManagement = new MenuManagement();
        }

        return menuManagement;
    }

}
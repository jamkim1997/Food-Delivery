package model;

import java.util.ArrayList;

public class RCategory {

    private final int rCatID;
    private String rCatName;
    private String rCatDescription;

    private ArrayList<Restaurant> restaurants;

    // For DBManager to use and pass necessary information to the controller
    public RCategory(int rCatID, String rCatName, String rCatDescription, ArrayList<Restaurant> restaurants) {
        this.rCatID = rCatID;
        this.rCatName = rCatName;
        this.rCatDescription = rCatDescription;
        this.restaurants = restaurants;
    }

    // For controller to use to create a RCategory object which can be passed to the DBManager for use
    // Initially, there is no restaurants
    public RCategory(String rCatName, String rCatDescription) {
        this.rCatID = -1;
        this.rCatName = rCatName;
        this.rCatDescription = rCatDescription;
    }

    // For controller to use to update a RCategory object which can be passed to the DBManager for use
    public RCategory(int rCatID, String rCatName, String rCatDescription) {
        this.rCatID = rCatID;
        this.rCatName = rCatName;
        this.rCatDescription = rCatDescription;
    }

    // To be used only when viewing list of RCategories a restaurant has, no concern for its desc.
    public RCategory(int rCatID, String rCatName) {
        this.rCatID = rCatID;
        this.rCatName = rCatName;
        this.rCatDescription = "";
    }

    public int getrCatID() {
        return rCatID;
    }

    public String getrCatName() {
        return rCatName;
    }

    public void setrCatName(String rCatName) {
        this.rCatName = rCatName;
    }

    public String getrCatDescription() {
        return rCatDescription;
    }

    public void setrCatDescription(String rCatDescription) {
        this.rCatDescription = rCatDescription;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}

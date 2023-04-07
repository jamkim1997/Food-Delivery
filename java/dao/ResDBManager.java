package dao;

import controller.rms.ModifyCategoryServlet;
import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResDBManager {

    private Statement st;
    private Statement st2;

    public ResDBManager(Connection conn, Connection conn2) throws SQLException {
        st = conn.createStatement();
        st2 = conn2.createStatement();
    }

    public ArrayList<Restaurant> findRestaurant(String name) throws SQLException, Exception {
        String fetch = "SELECT * FROM db.restaurant WHERE Restaurant_Name LIKE '%" + name + "%'";
        // Return list of restaurants matching a certain name pattern
        return getRestaurants(fetch);
    }

    // For Update Forms to retrieve up-to-date information for that particular restaurant
    public Restaurant findRestaurant(int id) throws SQLException, Exception {
        String fetch = "SELECT * FROM db.restaurant WHERE Restaurant_ID = " + id;
        // Return list of restaurants matching a certain name pattern
        return getRestaurants(fetch).get(0);
    }

    // If name is empty, this method will be called by the controller.
    public ArrayList<Restaurant> seeAllRestaurants() throws SQLException, Exception {
        String fetch = "SELECT * FROM db.restaurant";
        // Return all restaurants and their details
        return getRestaurants(fetch);
    }

    private ArrayList<Restaurant> getRestaurants(String fetch) throws SQLException, Exception {

        ResultSet rs = st.executeQuery(fetch);

        ArrayList<Restaurant> resList = new ArrayList<>();

        while (rs.next()) {

            int resID = Integer.parseInt(rs.getString("Restaurant_ID"));
            String resName = rs.getString("Restaurant_Name");
            String imageRef = rs.getString("Image_Reference");
            int streetNo = Integer.parseInt(rs.getString("Street_Number"));
            String streetName = rs.getString("Street_Name");
            int postcode = Integer.parseInt(rs.getString("Postcode"));
            String state = rs.getString("State");
            String suburb = rs.getString("Suburb");
            String country = rs.getString("Country");
            Boolean activated = (Integer.parseInt(rs.getString("Activated")) == 1);
            long abn = Long.parseLong(rs.getString("ABN"));
            String accountName = rs.getString("Account_Name");
            int bsb = Integer.parseInt(rs.getString("BSB"));
            int accountNumber = Integer.parseInt(rs.getString("Account_Number"));

            // Search for the categories below that restaurant
            ArrayList<RCategory> categories = getRestaurantCategories(resID);

            resList.add(new Restaurant(resID, imageRef, resName, categories, streetNo, streetName,
                    postcode, state, suburb, country, activated, abn, accountName, bsb, accountNumber));
        }

        // If it is empty, it is empty, we have the controller deal with that
        return resList;
    }

    private ArrayList<RCategory> getRestaurantCategories(int id) throws SQLException, Exception {
        String fetch = "SELECT RC.RCategory_ID, RCategory_Name " +
                "FROM db.restaurant_rcategory RR INNER JOIN db.restaurant R " +
                "INNER JOIN db.rcategory RC WHERE R.Restaurant_ID = " + id + " AND " +
                "RR.Restaurant_ID = R.Restaurant_ID AND RR.RCategory_ID = RC.RCategory_ID";

        // using st2 made from a 2nd db connection to allow concurrent querying to avoid closing ResultSet
        ResultSet rs = st2.executeQuery(fetch);

        ArrayList<RCategory> catList = new ArrayList<>();

        while (rs.next()) {

            int resID = Integer.parseInt(rs.getString("RC.RCategory_ID"));
            String catName = rs.getString("RCategory_Name");

            catList.add(new RCategory(resID, catName));
        }

        return catList;
    }

    // Use id to delete the right record
    public void deleteRestaurant(int id) throws SQLException, Exception {
            st.executeUpdate("DELETE FROM db.restaurant_rcategory WHERE Restaurant_ID = " + id);
            // Deletes any update request for the restaurant as well
            st.executeUpdate("DELETE FROM db.request WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.coupon_r WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.menu_item WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.staff WHERE Restaurant_ID = " + id);
            st.executeUpdate("DELETE FROM db.restaurant WHERE Restaurant_ID = " + id);
    }

    // Manual way of creating restaurants, for R1, AppStaff will manually add them
    // Also acts as an alternative way to add restaurants
    public void createRestaurant(Restaurant restaurant) throws SQLException, Exception {
        String fetch = "INSERT INTO db.restaurant VALUES (DEFAULT, '" + restaurant.getRestaurantName() + "', '"
                + restaurant.getImageReference() + "', " + restaurant.getStreetNum() + ", '" +
                restaurant.getStreetName() + "', " + restaurant.getPostcode() + ", '"
                + restaurant.getState() + "', '" + restaurant.getSuburb() + "', '" + restaurant.getCountry()
                + "', " + restaurant.getActivate() + ", " + restaurant.getAbn() + ", '"
                + restaurant.getAccountName() + "', " + restaurant.getBsb() + ", "
                + restaurant.getAccountNum() + ")";

        st.executeUpdate(fetch);
        // NOTE: Boolean is converted to 1/0 when inserted and when querying from DB, it returns an INT representation
    }

    // Manual way of updating restaurants, for R1, AppStaff will manually update them
    // Also acts as an alternative way to update restaurants
    public Restaurant updateRestaurant(Restaurant restaurant) throws SQLException, Exception {
        String fetch = "UPDATE db.restaurant SET Restaurant_Name = '" + restaurant.getRestaurantName()
                + "', Image_Reference = '" + restaurant.getImageReference()
                + "', Street_Number = " + restaurant.getStreetNum()
                + ", Street_Name = '" + restaurant.getStreetName()
                + "', Postcode = " + restaurant.getPostcode()
                + ", State = '" + restaurant.getState() + "', Suburb = '" + restaurant.getSuburb()
                + "', Country = '" + restaurant.getCountry()
                + "', Activated = " + restaurant.getActivate()
                + ", ABN = " + restaurant.getAbn() + ", Account_Name = '" + restaurant.getAccountName()
                + "', BSB = " + restaurant.getBsb() + ", Account_Number = " + restaurant.getAccountNum()
                + " WHERE Restaurant_ID = " + restaurant.getRestaurantID();

        st.executeUpdate(fetch);

        return findRestaurant(restaurant.getRestaurantID());
    }

    public int restaurantActivation(int id) throws SQLException, Exception {
        String fetch = "SELECT Activated FROM db.restaurant WHERE Restaurant_ID = " + id;

        ResultSet rs = st.executeQuery(fetch);

        if (rs.next()) {
            if (Integer.parseInt(rs.getString("Activated")) == 1) {
                st.executeUpdate("UPDATE db.restaurant SET Activated = 0 WHERE Restaurant_ID = " + id);
                return 0;
            }
            else {
                st.executeUpdate("UPDATE db.restaurant SET Activated = 1 WHERE Restaurant_ID = " + id);
                return 1;
            }
        }

        return -1;
    }

    public ArrayList<RCategory> seeAllRCategories() throws SQLException, Exception {
        String fetch = "SELECT * FROM db.rcategory";

        return getRCategories(fetch);
    }

    public ArrayList<RCategory> findRCategory(String name) throws SQLException, Exception {
        String fetch = "SELECT * FROM db.rcategory WHERE RCategory_Name LIKE '%" + name + "%'";

        return getRCategories(fetch);
    }

    // For Update Forms to retrieve up-to-date information for that particular category
    public RCategory findRCategory(int id) throws SQLException, Exception {
        String fetch = "SELECT * FROM db.rcategory WHERE RCategory_ID = " + id;

        return getRCategories(fetch).get(0);
    }

    private ArrayList<RCategory> getRCategories(String fetch) throws SQLException, Exception {
        ResultSet rs = st.executeQuery(fetch);

        ArrayList<RCategory> catList = new ArrayList<>();

        while (rs.next()) {
            int catID = Integer.parseInt(rs.getString("RCategory_ID"));
            String rCatName = rs.getString("RCategory_Name");
            String desc = rs.getString("RCategory_Description");

            ArrayList<Restaurant> restaurants = getCategoryRestaurants(catID);

            catList.add(new RCategory(catID, rCatName, desc, restaurants));
        }

        return catList;
    }

    private ArrayList<Restaurant> getCategoryRestaurants(int id) throws SQLException, Exception {
        String fetch = "SELECT R.Restaurant_ID, R.Restaurant_Name " +
                "FROM db.restaurant_rcategory RR INNER JOIN db.restaurant R " +
                "INNER JOIN db.rcategory RC WHERE RC.RCategory_ID = " + id + " AND " +
                "RR.Restaurant_ID = R.Restaurant_ID AND RR.RCategory_ID = RC.RCategory_ID";

        ResultSet rs = st2.executeQuery(fetch);

        ArrayList<Restaurant> resList = new ArrayList<>();

        while (rs.next()) {
            int resID = Integer.parseInt(rs.getString("R.Restaurant_ID"));
            String resName = rs.getString("Restaurant_Name");
            resList.add(new Restaurant(resID, resName));
        }

        return resList;
    }

    // Have controller pass a RCategory object so that the parameter is clean
    public void createCategory(RCategory category) throws SQLException, Exception {
        String fetch = "INSERT INTO db.rcategory VALUES (DEFAULT, '" + category.getrCatName() + "', '"
                + category.getrCatDescription() + "')";

        st.executeUpdate(fetch);
    }

    public RCategory updateCategory(RCategory category) throws SQLException, Exception {
        String fetch = "UPDATE db.rcategory SET RCategory_Name = '" + category.getrCatName() + "', " +
                "RCategory_Description = '" + category.getrCatDescription() + "' WHERE RCategory_ID = "
                + category.getrCatID();

        st.executeUpdate(fetch);

        return findRCategory(category.getrCatID());
    }

    // Delete by ID, we get the ID from the RCat object which was instantiated by the DB.
    public void deleteCategory(int id) throws SQLException, Exception {
        st.executeUpdate("DELETE FROM db.restaurant_rcategory WHERE RCategory_ID = " + id);
        st.executeUpdate("DELETE FROM db.rcategory WHERE RCategory_ID = " + id);
    }

    public void setRestaurantCategory(int restaurantID, int categoryID) throws SQLException, Exception {
        ResultSet rs = st.executeQuery("SELECT * FROM db.restaurant_rcategory WHERE Restaurant_ID = " + restaurantID
                        + " AND RCategory_ID = " + categoryID);

        if (rs.next()) {
            st.executeUpdate("DELETE FROM db.restaurant_rcategory WHERE RCategory_ID = " + categoryID
                    + " AND Restaurant_ID = " + restaurantID);
        }
        else {
            st.executeUpdate("INSERT INTO db.restaurant_rcategory VALUES (" + categoryID + ", " + restaurantID + ")");
        }

    }

}

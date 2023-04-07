package model;

import java.sql.Date;
import java.time.LocalDate;

public class DeliveryDriver extends User {
    private int driverID;
    private int userID;
    private String numberPlate;
    private String vehicleDescription;
    private String dAccountName;
    private int dBSB;
    private int dAccountNumber;

    public DeliveryDriver(int userID, String fname, String lname, String pass, String email, int phoneNo, Date date,
            int streetNo, String streetName, int postcode, String state, String suburb, String country,
            boolean activated, int driverID, String numberPlate, String vehicleDescription,
            String dAccountName, int dBSB, int dAccountNumber) {
        super(userID, fname, lname, pass, email, phoneNo, date, streetNo, streetName, postcode, state, suburb, country,
                activated);
        this.driverID = driverID;
        this.userID = userID;
        this.numberPlate = numberPlate;
        this.vehicleDescription = vehicleDescription;
        this.dAccountName = dAccountName;
        this.dBSB = dBSB;
        this.dAccountNumber = dAccountNumber;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public String getdAccountName() {
        return dAccountName;
    }

    public void setdAccountName(String dAccountName) {
        this.dAccountName = dAccountName;
    }

    public int getdBSB() {
        return dBSB;
    }

    public void setdBSB(int dBSB) {
        this.dBSB = dBSB;
    }

    public int getdAccountNumber() {
        return dAccountNumber;
    }

    public void setdAccountNumber(int dAccountNumber) {
        this.dAccountNumber = dAccountNumber;
    }

}

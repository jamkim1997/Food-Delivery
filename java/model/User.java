package model;

import java.sql.Date;
import java.time.LocalDate;

public class User {

    private int UserID;
    private String fname;
    private String lname;
    private String pass;
    private String email;
    private int phoneNo;
    private Date dob;
    private LocalDate dob2;
    private int streetNo;
    private String streetName;
    private int postcode;
    private String state;
    private String suburb;
    private String country;
    private Boolean activated;

    public User(){};
    // uses Date
    public User(int userID, String fname, String lname, String pass,
                String email, int phoneNo, Date dob, int streetNo, String streetName,
                int postcode, String state, String suburb, String country, Boolean activated) {
        this.UserID = userID;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activated = activated;
    }
    // uses LocalDate
    public User(int userID, String fname, String lname, String pass,
                String email, int phoneNo, LocalDate dob, int streetNo, String streetName,
                int postcode, String state, String suburb, String country, Boolean activated) {
        this.UserID = userID;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dob2 = dob;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activated = activated;
    }

    // for testing
    public User(int userID, String fname, String lname) {
        UserID = userID;
        this.fname = fname;
        this.lname = lname;
    }

    public int getUserID() {
        return UserID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public LocalDate getDob2() {
        return dob2;
    }

    public void setDob(LocalDate dob) {
        this.dob2 = dob;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
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

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}

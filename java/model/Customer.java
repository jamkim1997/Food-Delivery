package model;

import java.time.LocalDate;

import java.util.Date;

public class Customer {

    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private String password;
    private LocalDate dateOfBirth;

    private int streetNumber;
    private String streetName;
    private int postcode;
    private String state;
    private String suburb;
    private String country;
    private boolean activated;

    private int customerID;
    private String cardNumber;
    private LocalDate cardExpiration;
    private int cardPin;
    private String cardName;

    public Customer(int userID, String firstName, String lastName, String password, String email, int phone, LocalDate dateOfBirth, int streetNumber, String streetName, int postcode, String state, String suburb, String country, boolean activated, int customerID, String cardNumber, LocalDate cardExpiration, int cardPin, String cardName) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password; 
        this.dateOfBirth = dateOfBirth;

        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activated = activated;

        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardPin = cardPin;
        this.cardName = cardName;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCustomerId() {
        return customerID;
    }

    public void setCustomerId(Integer customerId) {
        this.customerID = customerId;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getPostcode() {
        return this.postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getSuburb() {
        return this.suburb;
    }

    public void setSuburb(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getCardExpiration() {
        return this.cardExpiration;
    }

    public void setCardExpiration(LocalDate cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    public Integer getCardPin() {
        return cardPin;
    }

    public void setCardPin(Integer cardPin) {
        this.cardPin = cardPin;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    

}

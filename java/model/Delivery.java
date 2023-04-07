package model;

public class Delivery {
    private int deliveryID;
    private int orderID;
    private int driverID;
    private String deliveryStreet;
    private String deliverySuburb;
    private String deliveryState;
    private String deliveryPostal;
    private double deliveryFee;
    private int driverRating;
    private String driverInstructions;
    private String driverFeedback;
    private double driverTip;

    public Delivery(
            int deliveryID,
            int orderID,
            int driverID,
            String deliveryStreet,
            String deliverySuburb,
            String deliveryState,
            String deliveryPostal,
            double deliveryFee,
            int driverRating,
            String driverInstructions,
            String driverFeedback,
            double driverTip) {
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.driverID = driverID;
        this.deliveryStreet = deliveryStreet;
        this.deliverySuburb = deliverySuburb;
        this.deliveryState = deliveryState;
        this.deliveryPostal = deliveryPostal;
        this.deliveryFee = deliveryFee;
        this.driverRating = driverRating;
        this.driverInstructions = driverInstructions;
        this.driverFeedback = driverFeedback;
        this.driverTip = driverTip;
    }

    public Delivery(
            int orderID,
            String deliveryStreet,
            String deliverySuburb,
            String deliveryState,
            String deliveryPostal,
            double deliveryFee,
            String driverInstructions) {
        this.orderID = orderID;
        this.deliveryStreet = deliveryStreet;
        this.deliverySuburb = deliverySuburb;
        this.deliveryState = deliveryState;
        this.deliveryPostal = deliveryPostal;
        this.deliveryFee = deliveryFee;
        this.driverInstructions = driverInstructions;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliverySuburb() {
        return deliverySuburb;
    }

    public void setDeliverySuburb(String deliverySuburb) {
        this.deliverySuburb = deliverySuburb;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryPostal() {
        return deliveryPostal;
    }

    public void setDeliveryPostal(String deliveryPostal) {
        this.deliveryPostal = deliveryPostal;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(int driverRating) {
        this.driverRating = driverRating;
    }

    public String getDriverInstructions() {
        return driverInstructions;
    }

    public void setDriverInstructions(String driverInstructions) {
        this.driverInstructions = driverInstructions;
    }

    public String getDriverFeedback() {
        return driverFeedback;
    }

    public void setDriverFeedback(String driverFeedback) {
        this.driverFeedback = driverFeedback;
    }

    public double getDriverTip() {
        return driverTip;
    }

    public void setDriverTip(double driverTip) {
        this.driverTip = driverTip;
    }

    @Override
    public String toString() {
        return "Delivery [deliveryID=" + deliveryID + ", orderID=" + orderID + ", driverID=" + driverID
                + ", deliveryStreet=" + deliveryStreet + ", deliverySuburb=" + deliverySuburb + ", deliveryState="
                + deliveryState + ", deliveryPostal=" + deliveryPostal + ", deliveryFee=" + deliveryFee
                + ", driverRating=" + driverRating + ", driverInstructions=" + driverInstructions + ", driverFeedback="
                + driverFeedback + ", driverTip=" + driverTip + "]";
    }

}

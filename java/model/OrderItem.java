package model;

public class OrderItem{

    private int orderID;
    private int itemID;
    private int quantity;
    private String comment;

    public OrderItem(int orderID, int itemID, int quantity, String comment) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.comment = comment;
    }

    public OrderItem(int orderID, int itemID, int quantity) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
    }
    
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}
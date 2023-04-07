package model;

public class Receipt {
    int orderID;
    int restaurantID;
    String orderType;
    String couponID;
    int itemID;
    int quantity;
    String comment;
    int total;
    int price;

    public Receipt(int orderID, int itemID, int quantity, String comment,
            int price, int total) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.comment = comment;
        this.price = price;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getRestaurantID() {
        return restaurantID;
    }
    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getCouponID() {
        return couponID;
    }
    public void setCouponID(String couponID) {
        this.couponID = couponID;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    
}

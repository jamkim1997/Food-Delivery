package model;

public class MenuItem {
    int itemID;
    int restarauntID;
    String itemType;
    int servings;
    float price;
    int calories;
    String image;
    String description;
    String ingredients;
    String allergy;
    int stock;

    public MenuItem(){};
    public MenuItem(int itemID, int restarauntID, String itemType, int servings, float price, int calories,
            String image, String description, String ingredients, String allergy, int stock) {
        this.itemID = itemID;
        this.restarauntID = restarauntID;
        this.itemType = itemType;
        this.servings = servings;
        this.price = price;
        this.calories = calories;
        this.image = image;
        this.description = description;
        this.ingredients = ingredients;
        this.allergy = allergy;
        this.stock = stock;
    }

    public MenuItem(int restarauntID, String itemType, int servings, float price, int calories,
            String image, String description, String ingredients, String allergy, int stock) {
        this.restarauntID = restarauntID;
        this.itemType = itemType;
        this.servings = servings;
        this.price = price;
        this.calories = calories;
        this.image = image;
        this.description = description;
        this.ingredients = ingredients;
        this.allergy = allergy;
        this.stock = stock;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public int getRestarauntID() {
        return restarauntID;
    }
    public void setRestarauntID(int restarauntID) {
        this.restarauntID = restarauntID;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public int getServings() {
        return servings;
    }
    public void setServings(int servings) {
        this.servings = servings;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String getAllergy() {
        return allergy;
    }
    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    
}

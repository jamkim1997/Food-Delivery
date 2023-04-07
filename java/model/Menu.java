package model;

public class Menu {
    private String name;
    private float price;
    private int calories;
    private String description;
    private String ingredients;
    private MenuCategory category;

    Menu(String name, float price, int calories, String description, String ingredients, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.ingredients = ingredients;
        this.category = category;
    }

    public void EditMenu(String name, float price, int calories, String description, String ingredients, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.ingredients = ingredients;
        this.category = category;
    }

    public MenuCategory GetCategory() {
        return category;
    }

    public void SetCategory(MenuCategory category) {
        this.category = category;
    }

    public String GetName() {
        return name;
    }

    public float GetPrice() {
        return price;
    }

    public String GetString() {
        return price + " " + price;
    }
    public int getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }
}
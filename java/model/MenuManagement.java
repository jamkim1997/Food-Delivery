package model;
import java.util.*;
import java.lang.Float;

public class MenuManagement {
   private List<MenuCategory> categories;
   private List<Menu> menus;

   public MenuManagement() {
    this.menus = new ArrayList<Menu>();
    this.categories = new ArrayList<MenuCategory>();

   }
   public List<Menu> GetAllMenus() {
    return menus;
   }

   public List<MenuCategory> GetAllCategories() {
    return categories;
   }
   
   public Menu AddMenu(String name, String price, String calories, String description, String ingredients, String category) {
    float floatPrice = Float.parseFloat(price);
    int intCalories = Integer.parseInt(calories);
    MenuCategory tempCat;
    System.out.println("Category name:" + category);
    for(MenuCategory cat : categories ) {
        System.out.println("Cat:" + cat.GetName());
        if(cat.GetName().equals(category)) {
            tempCat = cat;
            Menu newMenu = new Menu(name, floatPrice, intCalories, description, ingredients, tempCat);
            menus.add(newMenu);
            return newMenu;
        }
    }
    return null;
   }

   public void DeleteMenu(String name) {
        Menu targetMenu = FindMenuByName(name);
        if(targetMenu == null) {
            return;
        }

        menus.remove(targetMenu);
   }

   public Menu FindMenuByName(String name) {
        for (Menu menu : menus) {
            if(name.equals(menu.GetName())) {
                return menu;
            }
        }
        
        return null;
   }

   public MenuCategory FindCategoryByName(String name) {
        for (MenuCategory category : categories) {
            if(name.equals(category.GetName())) {
                return category;
            }
        }
        return null;
   }
   
   public MenuCategory AddCategory(String name) {
        MenuCategory category = new MenuCategory(name);
        categories.add(category);
        return category;
   }

   public void DeleteCategory(String name) {
        MenuCategory targetCategory = FindCategoryByName(name);
        if(targetCategory == null) {
            return;
        }

        categories.remove(targetCategory);
   }

   public boolean IsAvailable(String name, boolean isMenu){
    if(isMenu){
        for(Menu menu : menus){
            if(name.equals(menu.GetName())){
                return false;
            }
        }
        return true;
    }
    else {
        
        for(MenuCategory category : categories){
            if(name.equals(category.GetName())){
                return false;
            }
        }
        return true;
    }
   }

}
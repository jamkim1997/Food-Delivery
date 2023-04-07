package model;

import java.util.*;

public class MenuCategory {
    private String name;
    private List<Menu> items;

    MenuCategory(String name) {
        this.items = new ArrayList<Menu>();
        this.name = name;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public int GetNumOfItmesInCategory() {
        return this.items.size();
    }

    public List<Menu> GetAllMenus() {
        return items;
    }

    public void AddItem(Menu item) {
        items.add(item);
    }

    public void DeleteItem(Menu item){
        if(items.contains(item)) {
            items.remove(item);
        }
    }
}
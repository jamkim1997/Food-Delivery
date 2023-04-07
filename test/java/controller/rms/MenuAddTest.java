package controller.rms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import model.AppStaff;
import model.Menu;
import model.MenuCategory;
import model.MenuManagement;

import org.junit.Test;

import dao.DBManager;

import java.sql.Statement;

public class MenuAddTest {

    // Test for adding category with correct information in proper format
    @Test
    public void testAddCategory() throws Exception {
        MenuManagement menuManagement = new MenuManagement();
        int previousNumCategory = menuManagement.GetAllCategories().size();
        MenuCategory newCategory = menuManagement.AddCategory("BBQ");
        int currentNumCategory = menuManagement.GetAllCategories().size();
        
        //Should have proper name
        assertTrue(newCategory.GetName().length() > 0);
        //the size Should be increased
        assertTrue(currentNumCategory > previousNumCategory);
    }

    // Test for adding menu
    @Test
    public void testAddMenu() throws Exception {
        MenuManagement menuManagement = new MenuManagement();
        MenuCategory newCategory = menuManagement.AddCategory("BBQ");
        int previousNumMenu = menuManagement.GetAllMenus().size();
        Menu newMenu = menuManagement.AddMenu("Pork belly", "13.4", "123", newCategory.GetName(), null, newCategory.GetName());
        int currentNumMenu = menuManagement.GetAllMenus().size();
        //Check the attributes
        assertTrue(newMenu.GetName().length() > 0);
        assertTrue(newMenu.GetPrice() > 0);
        assertTrue(newMenu.GetCategory().GetName().length() > 0);
        //the size Should be increased
        assertTrue(currentNumMenu > previousNumMenu);
        //Check if the category is valid
        assertTrue(newMenu.GetCategory().GetName() == newCategory.GetName());
    }

    // Test for deleting menu
    @Test
    public void testDeleteMenu() throws Exception {
        MenuManagement menuManagement = new MenuManagement();
        MenuCategory newCategory = menuManagement.AddCategory("BBQ");
        Menu newMenu = menuManagement.AddMenu("Pork belly", "13.4", "123", newCategory.GetName(), null, newCategory.GetName());
        Menu anotherMenu = menuManagement.AddMenu("beef", "17.4", "123", "BBQ", null, newCategory.GetName());
        int previousNumMenu = menuManagement.GetAllMenus().size();
        menuManagement.DeleteMenu("Pork belly");
        int currentNumMenu = menuManagement.GetAllMenus().size();
        //the size Should be decreased
        assertTrue(currentNumMenu < previousNumMenu);
        for (Menu menu : menuManagement.GetAllMenus()) {
            assertTrue(menu.GetName() != newMenu.GetName());
        }
    }

}

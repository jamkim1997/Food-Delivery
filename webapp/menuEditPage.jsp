<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Float" %>

<!DOCTYPE html>
<html style="font-size: 16px;" lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>Menu d122d</title>
    
    <link rel="stylesheet" href="./css/menuTest.css" media="screen">
    <link rel="stylesheet" href="./css/menu.css" media="screen">
    <meta name="theme-color" content="#478ac9">

  </head>
 
  <%
      Restaurant resto = (Restaurant)session.getAttribute("currentResto");
      MenuManagement me = resto.GetMenuManagement();
      List<MenuCategory> categories = me.GetAllCategories();
   %>
   

  <body class="u-body u-overlap u-xl-mode" data-lang="en">
  <script>
  <%
    String newName = request.getParameter("newName");
    String oldName = request.getParameter("oldName");

    if(newName == null) {
        // Remove
        int countOfMenus = 0;
        MenuCategory removedCategory = me.FindCategoryByName(oldName);
        countOfMenus = removedCategory.GetAllMenus().size();
        if(countOfMenus == 0) {
            me.DeleteCategory(oldName);
        } 
        else {
        
        if(oldName.equals("unassigned")) {
            System.out.println("Unassigned folder cannot be removed");
        }
        else {
        boolean hasUnassignedForlder = false;
        MenuCategory unassignedCategory = null;

        for(MenuCategory category: categories) {
            //Check if unassigned folder exists
            if(category.GetName().equals("unassigned")) {
                hasUnassignedForlder = true;
                break;
            }
        }

        if(!hasUnassignedForlder) {
            //Create unassigned folder if doesnt exist
            unassignedCategory= me.AddCategory("unassigned");
        }
        else {
            //assign unassigned folder if exists
            for(MenuCategory category : categories) {
            if(category.GetName().equals("unassigned")) {
                unassignedCategory = category;
                break;
            }
        }
        }
        
        MenuCategory oldCategory = null;
        for(MenuCategory category : categories) {
            if(category.GetName().equals(oldName)) {
                oldCategory = category;
                break;
            }
        }
        for(Menu menu : oldCategory.GetAllMenus()) {
            menu.SetCategory(unassignedCategory);
        }

        me.DeleteCategory(oldName);
        }
        
    }
    }

    else {
        // Edit
        System.out.println("Edit category");
        if(me.IsAvailable(newName, false)){
            for(MenuCategory category : categories) {
                if(oldName.equals(category.GetName())) {
                    category.SetName(newName);
                }
            }
        }
        else {
             out.print("alert('The name has been already used')");
        }
        
    }
    
  %>
    window.location.replace("menuStaffPage.jsp");
  </script>
    
</body>

</html>
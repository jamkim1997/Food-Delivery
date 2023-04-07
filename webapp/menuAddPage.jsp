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
    <title>Menu Add</title>
    
    <link rel="stylesheet" href="./css/menuTest.css" media="screen">
    <link rel="stylesheet" href="./css/menu.css" media="screen">
    <meta name="theme-color" content="#478ac9">

  </head>
 
  <%
      Restaurant resto = (Restaurant)session.getAttribute("currentResto");
      MenuManagement me = resto.GetMenuManagement();
      List<Menu> menus = me.GetAllMenus();
   %>
   

  <body class="u-body u-overlap u-xl-mode" data-lang="en">
  <script>
  <%
    String name = request.getParameter("name");
    String oldName = request.getParameter("oldName");
    String price = request.getParameter("price");
    String calories = request.getParameter("calories");
    String description = request.getParameter("description");
    String ingredients = request.getParameter("ingredients");
    String category = request.getParameter("category");
    String type = request.getParameter("type");

    if(price == null) {
      //Add category
      if(me.IsAvailable(name, false)){
        me.AddCategory(name);
      }
      else {
        out.print("alert('The name has been already used')");
      }
      
    }
    else if(type.equals("add")){
      //Add menu
      if(me.IsAvailable(name, true)){
        Menu newMenu = me.AddMenu(name, price, calories, description, ingredients, category);
        if(newMenu != null){
          MenuCategory categoryInstance = me.FindCategoryByName(category);
          categoryInstance.AddItem(newMenu);
        }
      }
      else {
        out.print("alert('The name has been already used')");
      }
    }
    else {
      //Edit menu
      if(me.IsAvailable(name, true)){
        Menu target = null;
        for(Menu menu : menus) {
          if(oldName.equals(menu.GetName())) {
              //find menu
              MenuCategory categoryInstance = me.FindCategoryByName(category);
              menu.EditMenu(name, Float.parseFloat(price), Integer.parseInt(calories),description,ingredients,categoryInstance);
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
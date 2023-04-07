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
    <title>Menu Page</title>
    
    <link rel="stylesheet" href="./css/menuTest.css" media="screen">
    <link rel="stylesheet" href="./css/menu.css" media="screen">
    <meta name="theme-color" content="#478ac9">

 <%
 // Get Restaurant
    Staff loginedStaff = (Staff) session.getAttribute("staff");
      //MenuManagement me = staff.GetMenuManagement();
      MenuManagement me = new MenuManagement();
      List<Menu> menus = me.GetAllMenus();
      List<MenuCategory> categories = me.GetAllCategories();
      int categorySize = categories.size();
      if(categorySize == 0) {
        categorySize = 1;
        // For layout
      }
   %>
  </head>

<script>

</script>

  <body class="u-body u-overlap u-xl-mode" data-lang="en">
    <section class="u-clearfix u-section-1" id="sec-664f">
      <div class="u-clearfix u-sheet u-sheet-1">
        <img class="u-expanded-width u-image u-image-round u-radius-25 u-image-1" src="images/testRestoImage.png" alt="test image" data-image-width="960" data-image-height="1280">
        <p class="u-custom-font u-font-montserrat u-text u-text-default u-text-1">Delicous Central</p>
         <!-- Category table -->
        <div class="u-table u-table-responsive u-table-1">
          <table class="u-table-entity">
            <colgroup>
              <col width="100%">
            </colgroup>
            <tbody class="u-table-body">
            <% 
            if(categories.size() == 0) {
               out.print("<tr style='height: 46px;'><td class='u-align-left u-border-1 u-border-grey-dark-1 u-table-cell u-table-cell-1'>Empty</td></tr>");
            }
              for(MenuCategory category : categories) {
                if(!category.GetName().equals("unassigned")){
                  out.print("<tr style='height: 46px;'><td class='u-align-left u-border-1 u-border-grey-dark-1 u-table-cell u-table-cell-1'>" + category.GetName() + "</td></tr>");
                }
            }
            %>
            </tbody>
          </table>
        </div>

         <!-- Menus -->
         <%
          if(menus.size() == 0) {
            out.print("<h3 style='margin: "+ -55 * categorySize+ "px auto 0 285px;' class='u-text u-text-default'>Empty</h3>");
          }
          else {
            int value = -55 * categorySize;
            for(MenuCategory category : categories) {
              if(!category.GetName().equals("unassigned")){
                out.print("<h3 style='margin: "+ value + "px auto 0 285px;' class='u-text u-text-default'>"+ category.GetName() +"</h3>");
                out.print("<div class='u-list u-list-2'> <div class='u-repeater u-repeater-2'>");
                value = 0;

                for(Menu menu : menus) {
                  if(menu.GetCategory().GetName().equals(category.GetName())) {
                    out.print("<div class='u-container-style u-list-item u-repeater-item'><div class='u-container-layout u-similar-container u-container-layout-5'><p class='u-custom-font u-font-montserrat u-text u-text-default u-text-3'>");
                    out.print(menu.GetName() + "</p>");
                    out.print("<p>$" + menu.GetPrice() + "</p></div></div>");
                    }
                }
                out.print("</div></div>");
              }
            }
          }
        %>

      </div>
    </section>

</body>

</html>
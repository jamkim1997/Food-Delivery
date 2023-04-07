<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Staff: Additional Details</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
    <%
        String error = "";
        if (null != request.getAttribute("Error")) {
            error = (String) request.getAttribute("Error");
        }
    %>
    <body>
        <h1>Staff: Additional Details</h1>
        <br/>
        <form action="StaffAddDetailsServlet" method="post">
            <table id="registerTable">
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label>Restaurant ID:</label></td>
                    <td><input type="text" name="restaurantID" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Privilege</label></td>
                    <td><input type="text" name="privilege" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Position</label>
                    <td><input type="text" name="position" required="true"></td>
                </tr>  
            </table> 
            <div class="center">
                <input type ="submit" value="Add Details">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Staff Login</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
        <%
            String error = "";
            if (null != request.getAttribute("Fail")) {
                error = (String) request.getAttribute("Fail");
            }
        %>
    <body>
        <h1>Staff Login</h1>
        <br/>
        <form action="StaffLoginServlet" method="post">
            <table>
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label>Email:</label></td>
                    <td><input type="text" name="email" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Password:</label></td>
                    <td><input type="password" name="password" required="true"></td>
                </tr> 
            </table> 
            <div class="center">
                <input type ="submit" value="Login">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>
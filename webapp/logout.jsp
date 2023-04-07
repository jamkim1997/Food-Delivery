<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Staff Login</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
    <%-- <% session.invalidate(); %> --%>
    <%
        session.setAttribute("Staff", null);
        session.setAttribute("Customer", null);
        session.setAttribute("appStaff", null);
        session.setAttribute("Driver", null);
    %>
    
    <body>
        <h1>You have been logged out</h1>
        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="index">Go to Index Page</a>
    </body>
</html>
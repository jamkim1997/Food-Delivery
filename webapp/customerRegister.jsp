<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to the Register Page</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
        <%
            String error = "";
            if (null != request.getAttribute("Error")) {
                error = (String) request.getAttribute("Error");
            }
        %>
    <body>
        <h1>Register</h1>
        <br/>
        <form action="CustomerRegisterServlet" method="post">
            <table id="registerTable">
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <td><input type="text" name="firstName" required="true"></td>
                </tr> 
                <tr>
                    <td><label for="lastName">Last Name:</label></td>
                    <td><input type="text" name="lastName" required="true"></td>
                </tr> 
                <tr>
                    <td><label for="email">Email:</label>
                    <td><input type="text" name="email" required="true"></td>
                </tr>                
                <tr>
                    <td><label for="password">Password:</label>
                    <td><input type="password"  name="password" required="true"></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label>
                    <td><input type="text"  name="phone" required="true"></td>
                </tr>
                <tr>
                    <td><label for="dateOfBirth">Date of Birth</label>
                    <td><input type="date" name="dob" required="true"></td>
                </tr>
                <tr>
                    <td><label>Street Number</label>
                    <td><input type="text" name="streetNumber" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Street Name</label>
                    <td><input type="text" name="streetName" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Postcode</label>
                    <td><input type="text" name="postcode" required="true"></td>
                </tr> 
                <tr>
                    <td><label>State</label>
                    <td><input type="text" name="state" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Suburb</label>
                    <td><input type="text" name="suburb" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Country</label>
                    <td><input type="text" name="country" required="true"></td>
                </tr>
            </table> 
            <div class="center">
                <input type ="submit" value="Sign Up">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>
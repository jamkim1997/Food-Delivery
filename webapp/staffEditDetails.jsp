<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.Staff"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Details Page</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
    <body>
        <h1>Edit Details</h1>
        <%            
            Staff staff = (Staff) session.getAttribute("Staff");
            String error = "";
            if (request.getAttribute("Error") != null) {
                error = (String) request.getAttribute("Error");
            }
        %>
        <br/>
        <form action="StaffEditDetails" method="post">
            <table id="registerTable">
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <td><input type="text" name="firstName" required="true" value=<%=staff.getFname()%>></td>
                </tr> 
                <tr>
                    <td><label for="lastName">Last Name:</label></td>
                    <td><input type="text" name="lastName" required="true" value=<%=staff.getLname()%>></td>
                </tr> 
                <tr>
                    <td><label for="email">Email:</label>
                    <td><input type="text" name="email" required="true" value=<%=staff.getEmail()%>></td>
                </tr>                
                <tr>
                    <td><label for="password">Password:</label>
                    <td><input type="password"  name="password" required="true" value=<%=staff.getPass()%>></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label>
                    <td><input type="text"  name="phone" required="true" value=<%=staff.getPhoneNo()%>></td>
                </tr>
                <tr>
                    <td><label for="dateOfBirth">Date of Birth</label>
                    <td><input type="date" name="dob" required="true" value=<%=staff.getDob2()%>></td>
                </tr>
                <br />
                <tr>
                    <td><label>Street Number</label>
                    <td><input type="text" name="streetNumber" required="true" value=<%=staff.getStreetNo()%>></td>
                </tr> 
                <tr>
                    <td><label>Street Name</label>
                    <td><input type="text" name="streetName" required="true" value=<%=staff.getStreetName()%>></td>
                </tr> 
                <tr>
                    <td><label>Postcode</label>
                    <td><input type="text" name="postcode" required="true" value=<%=staff.getPostcode()%>></td>
                </tr> 
                <tr>
                    <td><label>State</label>
                    <td><input type="text" name="state" required="true" value=<%=staff.getState()%>></td>
                </tr> 
                <tr>
                    <td><label>Suburb</label>
                    <td><input type="text" name="suburb" required="true" value=<%=staff.getSuburb()%>></td>
                </tr> 
                <tr>
                    <td><label>Country</label>
                    <td><input type="text" name="country" required="true" value=<%=staff.getCountry()%>></td>
                </tr>
                <br />
                <tr>
                    <td><label>Restaurant ID</label>
                    <td><input type="text" name="restaurantID" required="true" value=<%=staff.getRestaurantID()%>></td>
                </tr>
                <tr>
                    <td><label>Privilege</label>
                    <td><input type="text" name="privilege" required="true" value=<%=staff.getPrivilege()%>></td>
                </tr>
                <tr>
                    <td><label>Position</label>
                    <td><input type="text" name="position" required="true" value=<%=staff.getPosition()%>></td>
                </tr>
               

            </table> 
            <div class="center">
                <input type ="submit" value="Update Details">
            </div>    
        </form>
        <a href="./index.jsp">Back to Main Page</a>
    </body>
</html>
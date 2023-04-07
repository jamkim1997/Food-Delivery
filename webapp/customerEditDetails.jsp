<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Details Page</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
        <%
            String error = "";
            if (null != request.getAttribute("Error")) {
                error = (String) request.getAttribute("Error");
            }
        %>
    <body>
        <h1>Edit Details</h1>
        <%            
            Customer customer = (Customer) session.getAttribute("Customer");
        %>
        <br/>
        <form action="CustomerEditDetails" method="post">
            <table id="registerTable">
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <td><input type="text" name="firstName" required="true" value=<%=customer.getFirstName()%>></td>
                </tr> 
                <tr>
                    <td><label for="lastName">Last Name:</label></td>
                    <td><input type="text" name="lastName" required="true" value=<%=customer.getLastName()%>></td>
                </tr> 
                <tr>
                    <td><label for="email">Email:</label>
                    <td><input type="text" name="email" required="true" value=<%=customer.getEmail()%>></td>
                </tr>                
                <tr>
                    <td><label for="password">Password:</label>
                    <td><input type="password"  name="password" required="true" value=<%=customer.getPassword()%>></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label>
                    <td><input type="text"  name="phone" required="true" value=<%=customer.getPhone()%>></td>
                </tr>
                <tr>
                    <td><label for="dateOfBirth">Date of Birth</label>
                    <td><input type="date" name="dob" required="true" value=<%=customer.getDateOfBirth()%>></td>
                </tr>
                <br />
                <tr>
                    <td><label>Street Number</label>
                    <td><input type="text" name="streetNumber" required="true" value=<%=customer.getStreetNumber()%>></td>
                </tr> 
                <tr>
                    <td><label>Street Name</label>
                    <td><input type="text" name="streetName" required="true" value=<%=customer.getStreetName()%>></td>
                </tr> 
                <tr>
                    <td><label>Postcode</label>
                    <td><input type="text" name="postcode" required="true" value=<%=customer.getPostcode()%>></td>
                </tr> 
                <tr>
                    <td><label>State</label>
                    <td><input type="text" name="state" required="true" value=<%=customer.getState()%>></td>
                </tr> 
                <tr>
                    <td><label>Suburb</label>
                    <td><input type="text" name="suburb" required="true" value=<%=customer.getSuburb()%>></td>
                </tr> 
                <tr>
                    <td><label>Country</label>
                    <td><input type="text" name="country" required="true" value=<%=customer.getCountry()%>></td>
                </tr>
                <br />
                <tr>
                    <td><label>Card Number</label>
                    <td><input type="text" name="cardNumber" required="true" value=<%=customer.getCardNumber()%>></td>
                </tr>
                <tr>
                    <td><label>Card Expiration</label>
                    <td><input type="date" name="cardExpiration" required="true" value=<%=customer.getCardExpiration()%>></td>
                </tr>
                <tr>
                    <td><label>Card Pin</label>
                    <td><input type="text" name="cardPin" required="true" value=<%=customer.getCardPin()%>></td>
                </tr>
                <tr>
                    <td><label>Card Name</label>
                    <td><input type="text" name="cardName" required="true" value=<%=customer.getCardName()%>></td>
                </tr>

            </table> 
            <div class="center">
                <input type ="submit" value="Update Details">
            </div>    
        </form>
        <a href="./index.jsp">Back to Main Page</a>
    </body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Payment Details</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
        <%
            String error = "";
            if (null != request.getAttribute("Error")) {
                error = (String) request.getAttribute("Error");
            }
        %>
    <body>
        <h1>Add Payment Details</h1>
        <br/>
        <form action="CustomerAddPaymentServlet" method="post">
            <table id="registerTable">
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label>Card Number:</label></td>
                    <td><input type="text" name="cardNumber" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Card Expiration:</label></td>
                    <td><input type="date" name="cardExpiration" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Card pin:</label>
                    <td><input type="text" name="cardPin" required="true"></td>
                </tr>                
                <tr>
                    <td><label>Card Name:</label>
                    <td><input type="text"  name="cardName" required="true"></td>
                </tr>
            </table> 
            <div class="center">
                <input type ="submit" value="Add Payment">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>
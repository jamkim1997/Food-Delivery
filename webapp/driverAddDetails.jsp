<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Driver Add Details</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
        <%
            String error = "";
            if (null != request.getAttribute("Error")) {
                error = (String) request.getAttribute("Error");
            }
        %>
    <body>
        <h1>Driver Add Details</h1>
        <br/>
        <form action="DriverAddDetails" method="post">
            <table id="registerTable">
                <tr><label><%=error%></label></tr>
                <tr>
                    <td><label>Number Plate</label></td>
                    <td><input type="text" name="numberPlate" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Vehicle Description</label></td>
                    <td><input type="date" name="vehicleDescription" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Bank Account Name</label>
                    <td><input type="text" name="dAccountName" required="true"></td>
                </tr>                
                <tr>
                    <td><label>Bank Account BSB</label>
                    <td><input type="text"  name="dBSB" required="true"></td>
                </tr>
                <tr>
                    <td><label>Bank Account Number</label>
                    <td><input type="text"  name="dAccountNumber" required="true"></td>
                </tr>
            </table> 
            <div class="center">
                <input type ="submit" value="Add Details">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>
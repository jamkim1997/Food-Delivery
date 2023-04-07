<%@page import="java.util.Iterator"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Receipt" %>
<%@ page import="controller.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/index.css">
        <title>Payment</title>
    </head>
    
    <% 
    ArrayList<Receipt> receipt = (ArrayList)session.getAttribute("receipt");

%>


<body>
<table style="width:100%">
    <h1>Show Order List</h1>
<tr>
    <th>Order ID</th>
    <th>Item ID</th>
    <th>Quantity</th>
    <th>Comment</th>
    <th>Price</th>
</tr>

<%
Iterator<Receipt> iterator = receipt.iterator();  

while(iterator.hasNext())  
{
    Receipt r = iterator.next(); 
%>
<tr>
<td><%=r.getOrderID()%></td>
<td><%=r.getItemID()%></td>
<td><%=r.getQuantity()%></td>
<td><%=r.getComment()%></td>
<td><%=r.getPrice()%></td>
</tr>

<%
	}
    request.setAttribute("OrderID", receipt.get(receipt.size() - 1).getOrderID());
%>
<tr></tr>
<tr></tr>

<tr>
    <td><b>Total</b></td>
    <td><b><%=receipt.get(receipt.size() - 1).getTotal()%></b></td>
</tr>

</table>

        <a href="menu.jsp" class="btn btn-dark text-light ms-2"> Back </a>
        <a href="createUpdateDelivery.jsp" class="btn btn-dark text-light ms-2"> Checkout </a>

    </body>
</html>
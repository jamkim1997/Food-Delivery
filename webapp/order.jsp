<%@page import="java.util.Iterator"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/index.css">
        <title>Order</title>
    </head>
    
    <% 
        ArrayList<Restaurant> restaurants = (ArrayList)session.getAttribute("restaurants");
    %>
    
    <body>
        <h1>Show restaurants List</h1>
        
       
        
<!-- <table cellspacing="2" cellpadding="2"> -->

<!-- <tr>
 <th>Restaurant Name</th>
 <th>Image</th>
 <th>Street Number</th>
 <th>Street Name</th>
 <th>Postcode</th>
 <th>State</th>
 <th>Suburb</th>
 <th>Country</th>
</tr> -->

<%
// Iterating through restaurants

	Iterator<Restaurant> iterator = restaurants.iterator();  // Iterator interface
	
	while(iterator.hasNext())  // iterate through all the data until the last record
	{
		Restaurant restaurant = iterator.next(); //assign individual employee record to the employee class object
	%>

    <!-- <p><%=restaurant.getImageReference()%></p> -->
    

	<!-- <tr>    
            <td><%=restaurant.getRestaurantName()%></td>
            <td><%=restaurant.getImageReference()%></td>
            <td><%=restaurant.getStreetNum()%></td>
            <td><%=restaurant.getStreetName()%></td>
            <td><%=restaurant.getPostcode()%></td>
            <td><%=restaurant.getState()%></td>
            <td><%=restaurant.getSuburb()%></td>
            <td><%=restaurant.getCountry()%></td>
	</tr> -->



    
        <div class="bs">
            <img src=<%="images/"+restaurant.getImageReference()%> width="200" height="200">
        <div class="flex-column">
            <a href="show-menuItems?RestaurantID=<%= restaurant.getRestaurantID()%>" class="btn btn-dark text-light ms-2"> <%=restaurant.getRestaurantName()%></a>
        </div>
    </div>
            

	<%
	}

%>
<!-- </table> -->

        <a href="index.jsp" class="btn btn-dark text-light ms-2"> Back </a>

    </body>
</html>
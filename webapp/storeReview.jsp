<%@page import="java.util.*"%> 
<%@page import="java.io.*"%>
<%@page import="dao.DBManager"%>
<%@page import="model.Staff"%>
<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/header.css" rel="stylesheet" type="text/css">
        <link href="./css/storeReview.css" rel="stylesheet" type="text/css">
        <title>Driver Review</title>
    </head>
    <body>
        <%
            DBManager manager = (DBManager) session.getAttribute("manager");
            Staff staff = (Staff) session.getAttribute("Staff");

            ArrayList<Order> orders = manager.getOrdersByResID(staff.getRestaurantID());
        %>

        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp">Home</a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <% if (staff != null) { %> 
                            <span>Hello, <%= staff.getFname()%></span>
                            <div class="user-menu">
                                <a class="header-button" href="staffEditDetails.jsp">View Account Details</a>
                                <a class="header-button" href="storeReview.jsp">View Reviews</a>
                                <a class="header-button" href="logout.jsp">Logout</a>
                            </div>
                        <% } else { %>
                            <a href="customerLogin.jsp">Login</a>
                            <span>&nbsp;/&nbsp;</span>
                            <a href="customerRegister.jsp">Register</a>
                        <% } %>
                    </div>
                </div>
            </div>

            <div class="header-outline"></div>
        </header>
                        
        <main>
            <p>Average Rating: <%= String.format("%,.1f", manager.getResRating(staff.getRestaurantID())) %></p>
            <% for (Order order : orders) { %>
                <div class="section" id="<%= order.getOrderID() %>">
                    <h1>Order no <span class="order-no"><%= order.getOrderID() %></span></h1>
                    <div class="order-details">
                        <p>Rate: 
                            <% if (order.getFoodRating() == 0) { %> 
                                <b>None</b> 
                            <% } else { %>
                                <b><%= order.getFoodRating() %></b>
                            <% } %>
                        </p>
                        <p>Feedback: 
                            <% if (order.getFoodFeedback() != null) { %> 
                                <span><%= order.getFoodFeedback() %></span>
                            <% } %>
                        </p>
                    </div>
                </div>
            <% } %>
        </main>
    </body>
</html>

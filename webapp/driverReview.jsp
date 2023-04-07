<%@page import="java.util.*"%> 
<%@page import="java.io.*"%>
<%@page import="dao.DBManager"%>
<%@page import="model.DeliveryDriver"%>
<%@page import="model.Delivery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/header.css" rel="stylesheet" type="text/css">
        <link href="./css/driverReview.css" rel="stylesheet" type="text/css">
        <title>Driver Review</title>
    </head>
    <body>
        <%
            DBManager manager = (DBManager) session.getAttribute("manager");
            DeliveryDriver driver = (DeliveryDriver) session.getAttribute("Driver");

            ArrayList<Delivery> deliveries = manager.getDeliveriesByDriverID(driver.getDriverID());
        %>

        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp">Home</a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <% if (driver != null) { %> 
                            <span>Hello, <%= driver.getFname()%></span>
                            <div class="user-menu">
                                <a class="header-button" href="driverEditDetails.jsp">View Account Details</a>
                                <a class="header-button" href="driverReview.jsp">View Reviews</a>
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
            <p>Average Rating: <%= String.format("%,.1f", manager.getDriverRating(driver.getDriverID())) %></p>
            <% for (Delivery delivery : deliveries) { %>
                <div class="section" id="<%= delivery.getDeliveryID() %>">
                    <h1>Delivery no <span class="delivery-no"><%= delivery.getDeliveryID() %></span></h1>
                    <div class="delivery-details">
                        <table>
                            <tr>
                                <td>Street:</td>
                                <td id="delivery-street"><%= delivery.getDeliveryStreet() %></td>
                            </tr>
                            <tr>
                                <td>Suburb:</td>
                                <td id="delivery-suburb"><%= delivery.getDeliverySuburb() %></td>
                            </tr>
                            <tr>
                                <td>State:</td>
                                <td id="delivery-state"><%= delivery.getDeliveryState() %></td>
                            </tr>
                            <tr>
                                <td>Postal:</td>
                                <td id="delivery-postal"><%= delivery.getDeliveryPostal() %></td>
                            </tr>
                            <tr>
                                <td>Postal:</td>
                                <td id="delivery-postal"><%= delivery.getDeliveryPostal() %></td>
                            </tr>
                            <tr>
                                <td>Instruction:</td>
                                <td id="driver-instructions"><%= delivery.getDriverInstructions() %></td>
                            </tr>
                        </table>
                        <hr />
                        <p>Rate: 
                            <% if (delivery.getDriverRating() == 0) { %> 
                                <b>None</b> 
                            <% } else { %>
                                <b><%= delivery.getDriverRating() %></b>
                            <% } %>
                        </p>
                        <p>Feedback: 
                            <% if (delivery.getDriverFeedback() != null) { %> 
                                <span><%= delivery.getDriverFeedback() %></span>
                            <% } %>
                        </p>
                    </div>
                </div>
            <% } %>
        </main>
    </body>
</html>

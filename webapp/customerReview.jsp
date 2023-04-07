<%@page import="java.util.*"%> 
<%@page import="java.io.*"%>
<%@page import="model.Customer"%>
<%@page import="model.Order"%>
<%@page import="model.Delivery"%>
<%@page import="dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/header.css" rel="stylesheet" type="text/css">
        <link href="./css/customerReview.css" rel="stylesheet" type="text/css">
        <title>Customer Review</title>
    </head>
    <body>
        <%
            Customer customer = (Customer) session.getAttribute("Customer");
            DBManager manager = (DBManager) session.getAttribute("manager");

            int orderID = 0;
            Order order = null;
            if (request.getAttribute("orderID") != null){
                orderID = (int) request.getAttribute("orderID");
                order = manager.getOrder(orderID);
            }

            int deliveryID = 0;
            Delivery delivery = null;
            if (request.getAttribute("deliveryID") != null){
                deliveryID = (int) request.getAttribute("deliveryID");
                delivery = manager.getDelivery(deliveryID);
            }
        %>

        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp">Home</a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <% if (customer != null) { %> 
                            <span>Hello, <%= customer.getFname()%></span>
                            <div class="user-menu">
                                <a class="header-button" href="customerEditDetails.jsp">View Account Details</a>
                                <a class="header-button" href="">View Orders</a>
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
            <% if (session.getAttribute("reviewErr") != null) { %>
                <b class="warning-text"><%= session.getAttribute("reviewErr") %></b>
            <% } %>

            <% if (orderID != 0) { %>
                <form action="update-review" method="POST">
                    <input type="hidden" name="orderID" value="<%= orderID %>">
                    <input type="hidden" name="deliveryID" value="<%= deliveryID %>">
                    <div class="order-review section">
                        <h1>Order no <span class="order-no"><%= orderID %></span></h1>
                        <hr />
                        <p>Rating:</p>
                        <div class="rate">
                            <input type="radio" id="order-star5" name="order-rate" value="5" 
                                        <% if (order.getFoodRating() == 5) { %>
                                            checked
                                        <% } %> />
                            <label for="order-star5" title="text">5 stars</label>
                            <input type="radio" id="order-star4" name="order-rate" value="4" 
                                        <% if (order.getFoodRating() == 4) { %>
                                            checked
                                        <% } %> />
                            <label for="order-star4" title="text">4 stars</label>
                            <input type="radio" id="order-star3" name="order-rate" value="3" 
                                        <% if (order.getFoodRating() == 3) { %>
                                            checked
                                        <% } %> />
                            <label for="order-star3" title="text">3 stars</label>
                            <input type="radio" id="order-star2" name="order-rate" value="2" 
                                        <% if (order.getFoodRating() == 2) { %>
                                            checked
                                        <% } %> />
                            <label for="order-star2" title="text">2 stars</label>
                            <input type="radio" id="order-star1" name="order-rate" value="1" 
                                        <% if (order.getFoodRating() == 1) { %>
                                            checked
                                        <% } %> />
                            <label for="order-star1" title="text">1 star</label>
                        </div>
                        <br />
                        <br />
                        <br />
                        <label for="order-feedback">Feedback: </label>
                        <textarea id="order-feedback" name="order-feedback" maxlength="100" rows="4" placeholder="<%= order.getFoodFeedback() %>"></textarea>
                    </div>
                    <% if (deliveryID != 0) { %>
                        <div class="delivery-review section">
                            <h1>Delivery no <span class="delivery-no"><%= deliveryID %></span></h1>
                            <hr />
                            <p>Rating:</p>
                            <div class="rate">
                                <input type="radio" id="delivery-star5" name="delivery-rate" value="5" 
                                        <% if (delivery.getDriverRating() == 5) { %>
                                            checked
                                        <% } %> />
                                <label for="delivery-star5" title="text">5 stars</label>
                                <input type="radio" id="delivery-star4" name="delivery-rate" value="4" 
                                        <% if (delivery.getDriverRating() == 4) { %>
                                            checked
                                        <% } %> />
                                <label for="delivery-star4" title="text">4 stars</label>
                                <input type="radio" id="delivery-star3" name="delivery-rate" value="3" 
                                        <% if (delivery.getDriverRating() == 3) { %>
                                            checked
                                        <% } %> />
                                <label for="delivery-star3" title="text">3 stars</label>
                                <input type="radio" id="delivery-star2" name="delivery-rate" value="2" 
                                        <% if (delivery.getDriverRating() == 2) { %>
                                            checked
                                        <% } %> />
                                <label for="delivery-star2" title="text">2 stars</label>
                                <input type="radio" id="delivery-star1" name="delivery-rate" value="1" 
                                        <% if (delivery.getDriverRating() == 1) { %>
                                            checked
                                        <% } %> />
                                <label for="delivery-star1" title="text">1 star</label>
                            </div>
                            <br />
                            <br />
                            <br />
                            <label for="delivery-feedback">Feedback: </label>
                            <textarea id="delivery-feedback" name="delivery-feedback" maxlength="100" rows="4" placeholder="<%= delivery.getDriverFeedback() %>"></textarea>
                        </div>
                    <% } %>
                    <button type="submit" class="submit-button">Submit</button>
                </form>
            <% } else { %>
                <b class="warning-text">Missing orderID</b>
            <% } %>
        </main>
    </body>
</html>

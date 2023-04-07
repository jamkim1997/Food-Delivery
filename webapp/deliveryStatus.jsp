<%@page import="java.util.*"%> 
<%@page import="java.io.*"%>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/deliveryStatus.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <script src="./js/deliveryStatus.js" defer></script>
        <script src="./js/updateData.js" defer></script>
        <title>Delivery Status</title>
    </head>
    <body>
        <%
            Customer customer = (Customer) session.getAttribute("Customer");
            int deliveryID = (int) request.getAttribute("deliveryID");
        %>
        <input id="deliveryID" value="<%= deliveryID %>" type="hidden"/>

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
            <h1>Delivery no <%= deliveryID %></h1>
            <div class="delivery">
                <ul class="status">
                    <li>
                        <div class="check-icon">
                            <img 
                                src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                id="order-received-img"
                            />
                        </div>
                        <b>Order Received</b>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img 
                                src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                id="prepared-img"
                            />
                        </div>
                        <b>Prepared</b>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img 
                                src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                id="delivered-img"
                            />
                        </div>
                        <b>Delivered</b>
                    </li>
                </ul>
                <hr />
                <table>
                    <tr>
                        <td>Order Status:</td>
                        <td id="order-status"></td>
                    </tr>
                    <tr>
                        <td>Order type:</td>
                        <td id="order-type"></td>
                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td id="order-street"></td>
                    </tr>
                    <tr>
                        <td>Suburb:</td>
                        <td id="order-suburb"></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td id="order-state"></td>
                    </tr>
                    <tr>
                        <td>Postal:</td>
                        <td id="order-postal"></td>
                    </tr>
                    <tr>
                        <td>Instruction:</td>
                        <td id="order-instructions"></td>
                    </tr>
                </table>
                <hr />
                <div class="actions">
                    <button class="delete-button" onclick="deleteDelivery(<%= deliveryID %>)">Delete</button>
                    <button class="update-button" href="createUpdateDelivery.jsp">Update</button>
                    <form action="prepare-review" method="POST">
                        <input type="hidden" name="deliveryID" value="<%= deliveryID %>"/>
                        <button type="submit" class="review-button">Review</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>

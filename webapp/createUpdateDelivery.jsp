<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/createDelivery.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <title>Place Delivery</title>
    </head>
    <body>
        <header>
            <%
                // for testing
                // session.setAttribute("user", new User(989898, "Minh Quan", "Tran"));
                // request.setAttribute("orderID", 101010);

                Customer customer = (Customer) session.getAttribute("Customer");
                int orderID = (int) request.getAttribute("orderID");
            %>

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
            <form action="create-update-delivery" method="post">
                <input name="orderID" value="<%= orderID %>" type="hidden"/>
                <h1>Delivery details</h1>
                <hr />
                <h3>
                    Order no <%= orderID %>
                    <% if (session.getAttribute("orderErr") != null) {%>
                        <span class="warning-text"><%= session.getAttribute("orderErr") %></span>
                    <% } %>
                </h3>
                <hr />
                <label>Order type</label>
                <ul id="order-type">
                    <li>
                        <label>
                            <input type="radio" id="delivery" name="order-type" value="Delivery" required checked>
                            Delivery
                        </label>
                    </li>
                    <li>
                        <label>
                            <input type="radio" id="pickup" name="order-type" value="Pickup" required>
                            Pickup
                        </label>
                    </li>
                    <li>
                        <label>
                            <input type="radio" id="dinein" name="order-type" value="Dinein" required>
                            Dine-in
                        </label>
                    </li>
                </ul>
                <hr />
                <div class="delivery-input">
                    <label>Delivery Address</label>
                    <div id="address" class="address">
                        <label for="street">Street</label>
                        <input type="text" id="street" name="street" required>
                        <label for="suburb">Suburb</label>
                        <input type="text" id="suburb" name="suburb" required>
                        <label for="state">State
                            <% if (session.getAttribute("stateErr") != null) {%>
                                <span class="warning-text"><%= session.getAttribute("stateErr") %></span>
                            <% } %>
                        </label>
                        <input type="text" id="state" name="state" required>
                        <label for="postal">Postal Code
                            <% if (session.getAttribute("postalErr") != null) {%>
                                <span class="warning-text"><%= session.getAttribute("postalErr") %></span>
                            <% }%>
                        </label>
                        <input type="text" id="postal" name="postal" required>
                    </div>
                    <label for="instructions">Instructions</label>
                    <textarea id="instructions" name="instructions" maxlength="100" rows="4"></textarea>
                    <hr />
                </div>
                <button type="submit">Place delivery</button>
            </form>
        </main>

        <script>
            const orderType = document.querySelector("#order-type");
            const delivery = document.querySelector("#delivery");
            const deliveryInput = document.querySelector(".delivery-input");

            orderType.addEventListener("click", () => {
                if (delivery.checked){
                    deliveryInput.style.display = "flex";
                } else {
                    deliveryInput.style.display = "none";
                }
            })
        </script>
    </body>
</html>

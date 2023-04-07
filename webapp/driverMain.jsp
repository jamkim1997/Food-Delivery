<%@page import="dao.DBManager"%>
<%@page import="model.User"%>
<%@page import="model.Order"%>
<%@page import="model.Delivery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/driverMain.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <script src="./js/driverMain.js" defer></script>
        <script src="./js/updateData.js" defer></script>
        <title>Driver Main</title>
    </head>
    <body>
        <%
            DBManager manager = (DBManager) session.getAttribute("manager");
            DeliveryDriver driver = (DeliveryDriver) session.getAttribute("Driver");
            double rating = manager.getDriverRating(driver.getDriverID());
            session.setAttribute("driver", driver);
        %>
        <input type="hidden" id="driver-no" value="<%= driver.getDriverID() %>">
        
        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp"><h3>Home</h3></a>
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
            <div class="section">
                <h3>Driver Details</h3>
                <table>
                    <tr>
                        <td>Full name:</td>
                        <td><%= driver.getFname() + " " + driver.getLname() %></td>
                    </tr>
                    <tr>
                        <td>Driver ID:</td>
                        <td><%= driver.getDriverID() %></td>
                    </tr>
                    <tr>
                        <td>User ID:</td>
                        <td><%= driver.getUserID() %></td>
                    </tr>
                    <tr>
                        <td>Number Plate:</td>
                        <td><%= driver.getNumberPlate() %></td>
                    </tr>
                    <tr>
                        <td>Vehicle Description:</td>
                        <td><%= driver.getVehicleDescription() %></td>
                    </tr>
                    <tr>
                        <td>Rating:</td>
                        <td><%= String.format("%,.1f", rating) %></td>
                    </tr>
                    <tr>
                        <td>Bank Account Name:</td>
                        <td><%= driver.getdAccountName() %></td>
                    </tr>
                    <tr>
                        <td>Bank State Branch (BSB):</td>
                        <td><%= driver.getdBSB() %></td>
                    </tr>
                    <tr>
                        <td>Bank Account Number:</td>
                        <td><%= driver.getdAccountNumber() %></td>
                    </tr>
                </table>
            </div>

            <div class="assigned-deliveries">
                <h3 class="title">Assigned Deliveries</h3>
                <div class="body">
                    <%-- <div class="section" id="101010">
                        <h1>Delivery no <span class="delivery-no">101010</span></h1>
                        <div class="delivery-details">
                            <ul class="status">
                                <li>
                                    <div class="check-icon">
                                        <img
                                            src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                            id="order-received-img"
                                        />
                                    </div>
                                    <b>Order received</b>
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
                                    <td>Street:</td>
                                    <td id="delivery-street"></td>
                                </tr>
                                <tr>
                                    <td>Suburb:</td>
                                    <td id="delivery-suburb"></td>
                                </tr>
                                <tr>
                                    <td>State:</td>
                                    <td id="delivery-state"></td>
                                </tr>
                                <tr>
                                    <td>Postal:</td>
                                    <td id="delivery-postal"></td>
                                </tr>
                                <tr>
                                    <td>Instruction:</td>
                                    <td id="driver-instructions"></td>
                                </tr>
                            </table>
                            <hr />
                            <div class="actions">
                                <form>
                                    <button class="cancel-button" onclick="updateDelivery(101010, 'Cancel')">Cancel</button>
                                    <button class="done-button" onclick="updateOrder(101010, 'Deliveried')">Done</button>
                                </form>
                            </div>
                        </div>
                    </div> --%>
                </div>
            </div>

            <div class="available-deliveries">
                <h3 class="title">Available Deliveries</h3>
                <div class="body">
                    <%-- <div class="section" id="101010">
                        <h1>Delivery no <span class="order-no">101010</span></h1>
                        <div class="delivery-details">
                            <ul class="status">
                                <li>
                                    <div class="check-icon">
                                        <img
                                            src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                            id="order-received-img"
                                        />
                                    </div>
                                    <b>Order received</b>
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
                                    <td>Street:</td>
                                    <td id="delivery-street"></td>
                                </tr>
                                <tr>
                                    <td>Suburb:</td>
                                    <td id="delivery-suburb"></td>
                                </tr>
                                <tr>
                                    <td>State:</td>
                                    <td id="delivery-state"></td>
                                </tr>
                                <tr>
                                    <td>Postal:</td>
                                    <td id="delivery-postal"></td>
                                </tr>
                                <tr>
                                    <td>Instruction:</td>
                                    <td id="driver-instructions"></td>
                                </tr>
                            </table>
                            <hr />
                            <div class="actions">
                                <form>
                                    <button class="accept-button" onclick="updateDelivery(101010, 989898)">Accept</button>
                                </form>
                            </div>
                        </div>
                    </div> --%>
                </div>
            </div> 
        </main>
    </body>
</html>

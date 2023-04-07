<%@page import="dao.DBManager"%>
<%@page import="model.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/storeOrder.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <script src="./js/storeOrder.js" defer></script>
        <script src="./js/updateData.js" defer></script>
        <title>Delivery Status</title>
    </head>
    <body>
        <%
            // for testing
            // session.setAttribute("staffeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXNzYWdlIjoiSldUIFJ1bGVzISIsImlhdCI6MTQ1OTQ0ODExOSwiZXhwIjoxNDU5NDU0NTE5fQ.-yIVBD5b73C75osbmwwshQNRC7frWUYrqaTjTpza2y4", new Staff(989898, "Minh Quan", "Tran", "ABC", "ASLDA", 12131, null, 131, "141", 1341, "SAD", "RLQK", "ASDKLJ", true, 123123, 303030, 1, ""));

            // Staff staff = (Staff) session.getAttribute("staffeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXNzYWdlIjoiSldUIFJ1bGVzISIsImlhdCI6MTQ1OTQ0ODExOSwiZXhwIjoxNDU5NDU0NTE5fQ.-yIVBD5b73C75osbmwwshQNRC7frWUYrqaTjTpza2y4");

            DBManager manager = (DBManager) session.getAttribute("manager");
            Staff staff = (Staff) session.getAttribute("Staff");
            session.setAttribute("staff", staff);
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
            <div>
                <%-- <div class="grid-item" id="-orderID-">
                    <div class="order-header">
                        <button class="order-cancel" onclick="updateOrder(-orderID-, 'Cancel')">Cancel</button> 
                        <h3 class="order-no">Order -orderID-</h3>
                        <button class="order-done" onclick="updateOrder(-orderID-, 'Prepared')">Done</button> 
                    </div>
                    <table class="food-list">
                        <tr>
                            <td>
                                -name-
                                <br />
                                &ensp;* -comment-
                            </td>
                            <td>x-quantity-</td>
                        </tr>
                    </table>
                    <p class="instructions">-instructions-</p>
                </div> --%>
            </div>
        </main>
    </body>
</html>

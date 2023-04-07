
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/index.css">

        <title>Main Menu</title>
    </head>
    <!--GETTING CUSTOMER AND STAFF OBJECTS-->
    <% Staff staff = (Staff) session.getAttribute("Staff"); %>
    <% Customer customer = (Customer) session.getAttribute("Customer"); %>

    <%-- If login as AppStaff, then AppStaff won't be null --%>
    <% AppStaff as = (AppStaff) session.getAttribute("appStaff"); %>

    <% String input = (session.getAttribute("input") != null) ? (String) session.getAttribute("input") : ""; %>

    <%-- Used to check if the appstaff has entered manageMode or not --%>
    <% Boolean manageMode = false;
       if (session.getAttribute("manageMode") == null) {
           session.setAttribute("manageMode", false);
           // Initially manageMode is null and not initialized
       }
       else {
           manageMode = (Boolean) session.getAttribute("manageMode");
       } %>

    <body class="overflow-hidden bg-light">

        <%--NavBar--%>
        <nav class="navbar navbar-expand-lg bg-light mb-3 card">
            <div class="container">
                <a class="h1 navbar-brand text-dark ms-2 mt-2" href="#">FDS</a>
                <div class="float-end me-2">
                    <%-- If not in manage mode, show the register/login etc... buttons, else only show management related buttons --%>
                    <% if (!manageMode) {
                        if (session.getAttribute("Customer") == null && session.getAttribute("Staff") == null && session.getAttribute("Driver") == null) { %>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./customerLogin.jsp">Customer Login</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./customerRegister.jsp">Customer Register</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./staffLogin.jsp">Staff Login</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./staffRegister.jsp">Staff Register</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./order.jsp">Order</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./driverLogin.jsp">Driver Login</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./driverRegister.jsp">Driver Register</a>
                        <% } else if (session.getAttribute("Customer") != null && session.getAttribute("Staff") == null && session.getAttribute("Driver") == null) { %>
                            <!-- BUTTONS FOR CUSTOMER-->
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./customerEditDetails.jsp">Edit Details</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./order.jsp">Order</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./logout.jsp">Log Out</a>
                        <% } else if (session.getAttribute("Staff") != null && session.getAttribute("Customer") == null && session.getAttribute("Driver") == null) { %>
                            <!-- BUTTONS FOR Staff-->
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./staffEditDetails.jsp">Edit Details</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./order.jsp">Order</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./logout.jsp">Log Out</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/empManage/showEmp">Emp Management</a>
                        <% } else { %>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./logout.jsp">Log Out</a>
                        <% } %>
                    <% } else { %>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="all-category">Manage Category</a>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="all-restaurant">Manage Restaurant</a>
                        <%-- this button placement needs to be placed in individual restaurants page --%>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="couponAppStaff.jsp">Coupon Management</a>
                    <% }
                    if (as != null) { %>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="manage-mode">
                            <%= (manageMode) ? "Exit Mode" : "Manage Mode"%>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./logout.jsp">AS LogOut</a>
                        </a>
                    <% } %>
                </div>
                <form id="searchForm" action="find-res" method="post" class="searchf d-flex me-5" role="search">
                    <input id="search" name="name" class="searchbar form-control me-1"
                           type="search" placeholder="Search" aria-label="Search"
                           value="<%=input%>">
                    <input type="hidden" name="view" value="menu">
                </form>
            </div>
        </nav>

        <%--Restaurants--%>
        <section class="container text-center">
            <div class="res row align-items-center justify-content-center">
                <%-- Ignore the unchecked cast, restaurants will only ever be ArrayList<Restaurants> or null --%>
                <% ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants"); %>
                <c:forEach items="${restaurants}" var="restaurant">
                    <c:if test="${(appStaff != null) || (appStaff == null && restaurant.activate == true)}">
                        <div class="col-lg-4 mt-5">
                            <div class="bs">
                                <img src="images/${restaurant.imageReference}" alt=${restaurant.imageReference}>
                                <div class="flex-column">
                                    <a>${restaurant.restaurantName}</a>
                                </div>
                                <% if (manageMode) { %>
                                    <div class="flex-column">
                                        <form class="mt-3" id="form${restaurant.restaurantID}"
                                              action="activate-res" method="post">
                                            <input id="switch" type="checkbox"
                                                   <c:choose>
                                                   <c:when test="${restaurant.activate == true}"> checked
                                                   </c:when> <c:otherwise> unchecked </c:otherwise> </c:choose>>
                                            <label class="d-inline-flex switches" for="switch" onclick="submitForm(${restaurant.restaurantID})"></label>
                                            <%-- Button is not spammable designed, not sure how to, need more research time --%>
                                            <input type="hidden" name="res" value="${restaurant.restaurantID}">
                                        </form>
                                    </div>
                                <% } %>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </section>
        <script>
            function submitForm(resID) {
                const form = document.getElementById("form" + resID);
                form.submit();
            }
            // DO NOT DELETE BELOW
            // const search = document.querySelector("#search");
            // const searchForm = document.getElementById("searchForm");
            // setInputFocus();
            //
            // search.addEventListener("input", updateRestaurants);
            // search.addEventListener("focus", updateFocus);
            // search.addEventListener("blur", updateBlur);
            //
            // function updateRestaurants(e) {
            //     searchForm.submit();
            // }
            //
            // function setInputFocus() {
            //     if (localStorage.getItem("focus") === "1") {
            //         document.getElementById("search").setSelectionRange(-1, -1);
            //         document.getElementById("search").focus();
            //     }
            // }
            //
            // function updateFocus(e) {
            //     localStorage.setItem("focus", "1");
            // }
            //
            // function updateBlur(e) {
            //     localStorage.setItem("focus", "0");
            // }
        </script>
    </body>

</html>
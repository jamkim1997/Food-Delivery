<%--
  Created by IntelliJ IDEA.
  User: Benz
  Date: 9/4/2022
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/manageResCat.css">
    <title>Manage Restaurant</title>
</head>

<body class="overflow-hidden bg-light">

<%
    String rDeleteSuccess = (String) session.getAttribute("rDeleteSuccess"); session.setAttribute("rDeleteSuccess", "");
    String rDeleteError = (String) session.getAttribute("rDeleteError"); session.setAttribute("rDeleteError", "");
%>

<div class="container-fluid text-center">

    <div class="mt-3 me-3">
        <a href="modifyRes.jsp" class="float-start btn btn-light text-dark ms-2"> Add </a>
        <a href="all-rescat?ref=res" class="float-start btn btn-light text-dark ms-2"> Assign Category </a>
        <% if (rDeleteSuccess != null && !rDeleteSuccess.equals("")) { %>
            <div class="d-inline-flex alert alert-success"><%=rDeleteSuccess%></div>
        <% }
        else if (rDeleteError != null && !rDeleteError.equals("")) { %>
            <div class="d-inline-flex alert alert-danger"><%=rDeleteError%></div>
        <% } %>
        <a href="index" class="float-end btn btn-light text-dark ms-2"> Exit </a>
    </div>

    <h1 class="h1 text-light mt-3 ms-5">Manage Category</h1>

    <% ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants"); %>

    <table class="table table-light mt-4">
        <thead>
        <tr>
            <th class="text-dark text-center ">Name</th>
            <th class="text-dark text-center ">Address</th>
            <th class="text-dark text-center ">Status</th>
            <th class="text-dark text-center ">Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${restaurants}" var="restaurant">
            <tr class="catrow">
                <td class="text-dark text-center ">${restaurant.restaurantName}</td>
                <td class="text-dark text-center ">${restaurant.streetNum} ${restaurant.streetName} ${restaurant.postcode} ${restaurant.suburb} ${restaurant.state} ${restaurant.country}</td>
                <td class="text-dark text-center ">
                    <c:choose>
                    <c:when test="${restaurant.activate == true}">
                    Active
                    </c:when>
                    <c:otherwise>
                    Disabled
                    </c:otherwise>
                    </c:choose></td>
                <td class="text-dark text-center">
                    <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="menu-update?id=${restaurant.restaurantID}">Edit Menu</a>
                    <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="find-res?id=${restaurant.restaurantID}">Edit</a>
                    <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="delete-res?res=${restaurant.restaurantID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
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
    <title>Manage Category</title>
</head>

<body class="overflow-hidden bg-light">

<%
    String cDeleteSuccess = (String) session.getAttribute("cDeleteSuccess"); session.setAttribute("cDeleteSuccess", "");
    String cDeleteError = (String) session.getAttribute("cDeleteError"); session.setAttribute("cDeleteError", "");
%>

    <div class="container-fluid text-center">

        <div class="mt-3 me-3">
            <a href="modifyCat.jsp" class="float-start btn btn-light text-dark ms-2"> Add </a>
            <a href="all-rescat?ref=cat" class="float-start btn btn-light text-dark ms-2"> Assign Category </a>
            <% if (cDeleteSuccess != null && !cDeleteSuccess.equals("")) { %>
                <div class="d-inline-flex alert alert-success"><%=cDeleteSuccess%></div>
            <% }
            else if (cDeleteError != null && !cDeleteError.equals("")) { %>
                <div class="d-inline-flex alert alert-danger"><%=cDeleteError%></div>
            <% } %>
            <a href="index" class="float-end btn btn-light text-dark ms-2"> Exit </a>
        </div>

        <h1 class="h1 text-light mt-3 ms-5">Manage Category</h1>

        <% ArrayList<RCategory> rcategories = (ArrayList<RCategory>) session.getAttribute("rcategories"); %>

        <table class="table table-light mt-4">
            <thead>
                <tr>
                    <th class="text-dark text-center ">Name</th>
                    <th class="text-dark text-center ">Description</th>
                    <th class="text-dark text-center ">Options</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${rcategories}" var="rcategory">
                    <tr class="catrow">
                        <td class="text-dark text-center ">${rcategory.rCatName}</td>
                        <td class="text-dark text-center ">${rcategory.rCatDescription}</td>
                        <td class="text-dark text-center">
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="find-cat?id=${rcategory.rCatID}">Edit</a>
                            <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="delete-cat?cat=${rcategory.rCatID}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>

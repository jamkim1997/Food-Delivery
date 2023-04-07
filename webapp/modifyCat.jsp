<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/4/2022
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/modifyResCat.css">
    <% String edit = (String) request.getParameter("edit"); %>
    <title>
        <%= (edit == null) ? "Add Category" : "Edit Category" %>
    </title>
</head>
<body>
    <%
        String success = (String) session.getAttribute("cModifySuccess");
        String nameError = (String) session.getAttribute("catNameError");
        String descError = (String) session.getAttribute("catDescError");
        RCategory rcategory = (RCategory) session.getAttribute("rcategory");
    %>

    <div class="container">
        <h1 class="h1 mb-4 mt-3"><%= (edit == null) ? "Add Category" : "Edit Category" %></h1>

        <form action="modify-category-details" method="post" id="modifyForm">

            <% if (success != null && !success.equals("")) { %>
                <div class="mt-3 alert alert-success"><%=success%></div>
            <% } %>

            <div class="form-group">
                <div class="mb-3 mr-10 col-xs-2">
                    <label for="category-name" class="form-label">Category Name</label>
                    <input type="text" name="name" class="form-control" id="category-name" aria-describedby="nameHelp"
                           value="<%= (rcategory != null) ? rcategory.getrCatName() : "" %>">
                    <div id="nameHelp" class="form-text"><%= (nameError != null) ? nameError : "Enter Email" %></div>
                </div>
            </div>

            <div class="form-group">
                <div class="mb-3 mr-10 col-xs-2">
                    <label class="form-label">Description (100 characters max)</label>
                    <%-- Do not indent the textarea, as it will include the spaces --%>
                    <textarea name="description" class="form-control" form="modifyForm" aria-describedby="descriptionHelp"><%= (rcategory != null) ? rcategory.getrCatDescription() : "" %></textarea>
                    <div id="descriptionHelp" class="form-text"><%= (descError != null) ? descError : "Enter Description" %></div>
                </div>
            </div>

            <input type="hidden" name="id"
                   value="<%= (rcategory != null) ? rcategory.getrCatID(): "" %>">

            <div class="form-group mb-5">
                <span>
                    <button type="submit" class="btn mb-5">Submit</button>
                    <a href="all-category" class="btn mb-5">Exit</a>
                </span>
            </div>

        </form>
    </div>

</body>
</html>

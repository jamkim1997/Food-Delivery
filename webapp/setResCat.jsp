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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css" integrity="sha512-mR/b5Y7FRsKqrYZou7uysnOdCIJib/7r5QeJMFvLNHNhtye3xJp1TdJVPLtetkukFn227nKpXD9OjUc09lx97Q==" crossorigin="anonymous"
          referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="css/setResCat.css">
    <title>Manage Restaurant</title>
</head>

<body class="overflow-hidden bg-light">

<%
    String ref = request.getParameter("ref");
%>

    <div class="container-fluid text-center">

        <div class="mt-3 me-3">
            <a href="<% if (ref.equals("cat")) { %> all-category <% } else { %> all-restaurant <% } %>"
               class="float-end btn custom-btn btn-light text-dark ms-2"> Exit </a>
        </div>

        <h1 class="h1 text-light mt-3 ms-5">Manage Category</h1>

        <% ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants"); %>
        <% ArrayList<RCategory> rcategories = (ArrayList<RCategory>) session.getAttribute("rcategories"); %>

        <table class="table table-light mt-4">
            <thead>
            <tr>
                <th class="text-dark text-center ">Name</th>
                <th class="text-dark text-center ">Categories</th>
            </tr>
            </thead>
            <tbody>
            <%-- Extremely slow algorithhm with O(n^3), need more research time to optimize this --%>
            <% for (Restaurant res : restaurants) { %>
                <tr class="catrow">
                    <td class="text-dark text-center "><%=res.getRestaurantName()%></td>
                    <td class="text-dark text-center">
                        <select class="selectpicker" multiple data-live-search="true"
                                <%-- onchange="updateResCat(<%=res.getRestaurantID()%>, <%=value%>)" --%>>
                            <%-- print all selected categories first --%>
                            <% for (RCategory selected : res.getCategories()) { %>
                                <option value="<%=res.getRestaurantID()%> <%=selected.getrCatID()%>" selected><%=selected.getrCatName()%></option>
                            <% } %>
                            <%-- print all remaining categories --%>
                            <% for (RCategory all : rcategories) { %>
                                <% boolean match = false; %>
                                <% for (RCategory selected : res.getCategories()) { %>
                                    <% if (selected.getrCatID() == all.getrCatID()) {
                                        match = true;
                                        break;
                                    } %>
                                <% } %>
                                <% if (!match) { %>
                                <option value="<%=res.getRestaurantID()%> <%=all.getrCatID()%>"><%=all.getrCatName()%></option>
                                <% } %>
                            <% } %>
                        </select>
                        <form class="mt-3" id="selectForm" action="update-rescat" method="post">
                            <input type="hidden" name="res" id="hidRes">
                            <input type="hidden" name="cat" id="hidCat">
                            <input type="hidden" name="ref" value="<%=ref%>">
                        </form>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js" integrity="sha512-FHZVRMUW9FsXobt+ONiix6Z0tIkxvQfxtCSirkKc5Sb4TKHmqq1dZa8DphF0XqKb3ldLu/wgMa8mT6uXiLlRlw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

    $(".selectpicker").on("changed.bs.select", function(e, clickedIndex) {
        const params = $(this).find('option').eq(clickedIndex).val();
        updateResCat(params);
    });

    function updateResCat(params) {
        const output = params.split(" ");
        document.getElementById("hidRes").value = output[0];
        document.getElementById("hidCat").value = output[1];
        const form = document.getElementById("selectForm");
        form.submit();
    }
</script>
</body>
</html>

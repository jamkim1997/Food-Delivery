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
        <%= (edit == null) ? "Add Restaurant" : "Edit Restaurant" %>
    </title>
</head>
<body>
<%
    String success = (String) session.getAttribute("rModifySuccess");
    Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
    String strNameError = (String) session.getAttribute("rStrNameError");
    String postCodeError = (String) session.getAttribute("rPostCodeError");
    String stateError = (String) session.getAttribute("rStateError");
    String suburbError = (String) session.getAttribute("rSuburbError");
    String countryError = (String) session.getAttribute("rCountryError");
    String ABNError = (String) session.getAttribute("rABNError");
    String acctNumError = (String) session.getAttribute("rAcctNumError");
    String acctNameError = (String) session.getAttribute("rAcctNameError");
    String BSBError = (String) session.getAttribute("rBSBError");
    String strNumError = (String) session.getAttribute("rStrNumError");
    String resNameError = (String) session.getAttribute("rResNameError");
%>

<div class="container">
    <h1 class="h1 mb-4 mt-3"><%= (edit == null) ? "Add Restaurant" : "Edit Restaurant" %></h1>

    <form action="modify-restaurant-details" method="post" id="modifyForm">

        <% if (success != null && !success.equals("")) { %>
            <div class="mt-3 alert alert-success"><%=success%></div>
        <% } %>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="restaurant-name" class="form-label">Restaurant Name</label>
                <input type="text" name="resName" class="form-control" id="restaurant-name" aria-describedby="resNameHelp"
                       value="<%= (restaurant != null) ? restaurant.getRestaurantName() : "" %>">
                <div id="resNameHelp" class="form-text">
                    <%= (resNameError != null) ? resNameError : "Enter Restaurant Name" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="street-number" class="form-label">Street Number</label>
                <input type="number" name="strNum" class="form-control" id="street-number" aria-describedby="strNumHelp"
                       value="<%= (restaurant != null) ? restaurant.getStreetNum() : "" %>">
                <div id="strNumHelp" class="form-text">
                    <%= (strNumError != null) ? strNumError : "Enter Street Number" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="street-name" class="form-label">Street Name</label>
                <input type="text" name="strName" class="form-control" id="street-name" aria-describedby="strNameHelp"
                       value="<%= (restaurant != null) ? restaurant.getStreetName() : "" %>">
                <div id="strNameHelp" class="form-text">
                    <%= (strNameError != null) ? strNameError : "Enter Street Name" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="postcode" class="form-label">Postcode</label>
                <input type="number" name="postcode" class="form-control" id="postcode" aria-describedby="postcodeHelp"
                       value="<%= (restaurant != null) ? restaurant.getPostcode() : "" %>">
                <div id="postcodeHelp" class="form-text">
                    <%= (postCodeError != null) ? postCodeError : "Enter Restaurant Name" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="state" class="form-label">State</label>
                <input type="text" name="state" class="form-control" id="state" aria-describedby="stateHelp"
                       value="<%= (restaurant != null) ? restaurant.getState() : "" %>">
                <div id="stateHelp" class="form-text">
                    <%= (stateError != null) ? stateError : "Enter State" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="suburb" class="form-label">Suburb</label>
                <input type="text" name="suburb" class="form-control" id="suburb" aria-describedby="suburbHelp"
                       value="<%= (restaurant != null) ? restaurant.getSuburb() : "" %>">
                <div id="suburbHelp" class="form-text">
                    <%= (suburbError != null) ? suburbError : "Enter Suburb" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="country" class="form-label">Country</label>
                <input type="text" name="country" class="form-control" id="country" aria-describedby="countryHelp"
                       value="<%= (restaurant != null) ? restaurant.getCountry() : "" %>">
                <div id="countryHelp" class="form-text">
                    <%= (countryError != null) ? countryError : "Enter Country" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="abn" class="form-label">ABN</label>
                <input type="number" name="abn" class="form-control" id="abn" aria-describedby="abnHelp"
                       value="<%= (restaurant != null) ? restaurant.getAbn() : "" %>">
                <div id="abnHelp" class="form-text">
                    <%= (ABNError != null) ? ABNError : "Enter ABN" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="account-name" class="form-label">Account Name</label>
                <input type="text" name="acctName" class="form-control" id="account-name" aria-describedby="acctNameHelp"
                       value="<%= (restaurant != null) ? restaurant.getAccountName() : "" %>">
                <div id="acctNameHelp" class="form-text">
                    <%= (acctNameError != null) ? acctNameError : "Enter Account Name" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="bsb" class="form-label">BSB</label>
                <input type="number" name="bsb" class="form-control" id="bsb" aria-describedby="bsbHelp"
                       value="<%= (restaurant != null) ? restaurant.getBsb() : "" %>">
                <div id="bsbHelp" class="form-text">
                    <%= (BSBError != null) ? BSBError : "Enter BSB" %>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="account-number" class="form-label">Account Number</label>
                <input type="number" name="acctNum" class="form-control" id="account-number" aria-describedby="acctNumHelp"
                       value="<%= (restaurant != null) ? restaurant.getAccountNum() : "" %>">
                <div id="acctNumHelp" class="form-text">
                    <%= (acctNumError != null) ? acctNumError : "Enter Account Number" %>
                </div>
            </div>
        </div>

        <input type="hidden" name="id"
               value="<%= (restaurant != null) ? restaurant.getRestaurantID() : "" %>">

        <div class="form-group mb-5">
                <span>
                    <button type="submit" class="btn mb-5">Submit</button>
                    <a href="all-restaurant" class="btn mb-5">Exit</a>
                </span>
        </div>

    </form>
</div>

</body>
</html>

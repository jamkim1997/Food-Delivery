<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/23/2022
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>AppStaff Login Page</title>
</head>

<body>

<%
    String emailErr = (String) session.getAttribute("emailErr"); session.setAttribute("emailErr", "");
    String passwordErr = (String) session.getAttribute("passwordErr"); session.setAttribute("passwordErr", "");
    String credentialsErr = (String) session.getAttribute("credentialsErr"); session.setAttribute("credentialsErr", "");
    session.setAttribute("appStaff", null); // anytime we enter this page, it will automatically log us out
%>

<%-- benzchua31@gmail.com ro0T!Ro0T! --%>

<div class="container">
    <h1 class="h1 mb-4 mt-3">Sign In</h1>

    <form action="appstaff-login" method="post">

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label for="InputEmai1" class="form-label">Email</label>
                <input type="text" name="email" class="form-control" id="InputEmai1" aria-describedby="emailHelp">
                <div id="emailHelp" class="form-text"> <%= (emailErr != null ? emailErr : "Enter Email") %> </div>
            </div>
        </div>

        <div class="form-group">
            <div class="mb-3 mr-10 col-xs-2">
                <label class="form-label">Password</label>
                <input type="password" name="pass" class="form-control" aria-describedby="passHelp">
                <div id="passHelp" class="form-text"> <%= (passwordErr != null ? passwordErr : "Enter Password") %> </div>
            </div>
        </div>

        <div class="form-group">
            <span>
                <button type="submit" class="btn btn-primary">Submit</button>
            </span>
            <% if (credentialsErr != null && !credentialsErr.isEmpty()) { %>
                <div class="mt-3 alert alert-danger"><%= credentialsErr %></div>
            <% } %>
        </div>

    </form>

</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous">
    </script>
    <%-- Dynamically set the base path for the entire page --%>
    <base href ="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>Emp Management</title>
</head>
<c:if test="${not empty success}">
    <script>
        $(document).ready(function() {
            $("#div1").fadeIn();
            $("#div1").fadeOut(2000);
        });
    </script>
</c:if>
<c:if test="${not empty fail}">
    <script>
        $(document).ready(function() {
            $("#div2").fadeIn();
            $("#div2").fadeOut(2000);
        });
    </script>
</c:if>
<body>
    <div class = "container">
        <h1 class="h1 text-center">Emp List</h1>
        <a href="index.jsp" class="position-absolute top-0 end-10 btn btn-info btn-lg">Main</a>
        <div class="alert alert-success" style="display:none" id="div1">Successful!</div>
        <div class="alert alert-danger" style="display:none" id="div2">Invalid privilege!</div>
        <div class="modal fade" id="EditModal" tabindex="-1" aria-labelledby="EditLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="EditLabel">Set permission or position</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="empManage/edit" class ="form-horizontal" method="post">
                        <div class="modal-body">
                            <input type="hidden" id="staffId" name="staffId" value=""/>
                            <div class = "form-floating mb-2">
                                <input type="text" id="privilege" name="privilege" class="form-control" value=""/>
                                <label for="privilege" class="col-md-1 control-label">Privilege</label>
                            </div>
                            <div class = "form-floating mb-2">
                                <input type="text" id="position" name="position" class="form-control" value=""/>
                                <label for="position" class="col-md-1 control-label">Position</label>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Save changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Privilege</th>
                <th>Position</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${StoreEmps}" var="staff">
                <tr>
                    <td>${staff.fname} ${staff.lname}</td>
                    <td>${staff.phoneNo}</td>
                    <td>${staff.email}</td>
                    <td>${staff.privilege}</td>
                    <td>${staff.position}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#EditModal"
                                data-id="${staff.staffID}" data-privilege="${staff.privilege}" data-position="${staff.position}">Edit</button>
                        <a href="empManage/removeEmp?id=${staff.staffID}" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<script>
    $('#EditModal').on('show.bs.modal', function (event) {
        $(this).find('#staffId').val($(event.relatedTarget).data('id'));
        $(this).find('#privilege').val($(event.relatedTarget).data('privilege'));
        $(this).find('#position').val($(event.relatedTarget).data('position'));
    })
</script>
</body>
</html>

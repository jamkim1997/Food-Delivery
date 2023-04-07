<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous">
    </script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.12.1/b-2.2.3/b-colvis-2.2.3/sl-1.4.0/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.12.1/b-2.2.3/b-colvis-2.2.3/sl-1.4.0/datatables.min.js"></script>
    <%-- Dynamically set the base path for the entire page --%>
    <base href ="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>Coupon</title>
    <script type = "text/javascript" src="./js/couponAppStaff.js"></script>
</head>
<body>
    <!-- TopBar -->
    <div id="topBar" style="height: 55px; background-color: #29bfc2; width: 100%;">
        <!-- Text in topBar -->
        <div style="position: absolute; top: 12px; left: 80px; font-size: 20px; color: whitesmoke">Coupon Management</div>
        <!-- Close Button in topBar -->
        <div style="position: absolute; top: 14px; right: 11px;">
            <button type="button" id = "page-close-btn" class="btn-close" aria-label="Close"></button>
        </div>
    </div>

    <!-- All content below topBar-->
    <div style="position: absolute;top: 58px; bottom: 30px; left: 0px; right: 0px;">
        <div class = "row">
            <!-- Navbar -->
            <div class="col-3">
                <div class="nav navbar-light bg-light flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <button class="nav-link active" id="v-pills-create-tab" data-bs-toggle="pill" data-bs-target="#v-pills-create" type="button" role="tab" aria-controls="v-pills-create" aria-selected="true">Create Coupon</button>
                    <button class="nav-link" id="v-pills-list-tab" data-bs-toggle="pill" data-bs-target="#v-pills-list" type="button" role="tab" aria-controls="v-pills-list" aria-selected="false">Coupon Lists</button>
                    <!-- -----------------------------------Incomplete -------------------
                    <button class="nav-link" id="v-pills-distribution-tab" data-bs-toggle="pill" data-bs-target="#v-pills-distribution" type="button" role="tab" aria-controls="v-pills-distribution" aria-selected="false" disabled>Distribute Coupon</button>
                    <button class="nav-link" id="v-pills-state-tab" data-bs-toggle="pill" data-bs-target="#v-pills-state" type="button" role="tab" aria-controls="v-pills-state" aria-selected="false" disabled>All Coupon State</button>
                    -->
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <!---------------------------------------- Create From -------------------------------------------------------->
                    <div class="tab-pane fade show active" id="v-pills-create" role="tabpanel" aria-labelledby="v-pills-create-tab" tabindex="0">
                        <!-- successful alert -->
                        <div class="alert alert-success" style="display:none" id="create-success-alert">Create Successful!</div>
                        <!-- error alert -->
                        <div class="alert alert-danger" style="display:none" id="create-falied-alert">Create Failed!(Server Side)</div>
                        <form action="coupon/create" method="post" class="needs-validation" novalidate id="create-form">
                            <!-- Coupon Name -->
                            <div class="row mb-3">
                                <label for="inputCouponName" class="col-sm-2 col-form-label">&#160;Name</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="inputCouponName" maxlength="25" required>
                                    <div class="invalid-feedback">
                                        Please provide a name(max 25 length).
                                    </div>
                                </div>
                            </div>
                            <!-- Coupon Scope -->
                            <div class="row mb-3">
                                <label for="inputCouponScope" class="col-sm-2 col-form-label">&#160;Scope</label>
                                <div class="col-sm-6">
                                    <select class="form-select" id="inputCouponScope">
                                        <option selected>All store</option>
                                        <option>Specific store</option>
                                        <option>Specific items in specific store</option>
                                    </select>
                                    <!-- Restaurant option -->
                                    <div class="row mb-9" id="scope-new-row" style="display: none">
                                    <div class="col-sm-6" id="scope-res" style="display: none">
                                        <br>
                                        <label for="inputScopeRes">Restaurant ID</label>
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" id="inputScopeRes" aria-describedby="button-res" onkeydown="return false;" style="caret-color: transparent !important;">
                                            <button class="btn btn-outline-secondary class-button-res" type="button" id="button-res" data-bs-toggle="modal" data-bs-target="#resSelect">Select</button>
                                            <div class="invalid-feedback">
                                                Please select restaurant.
                                            </div>
                                        </div>
                                    </div>
                                        <!-- Item option -->
                                        <div class="col-sm-5" id="scope-item" style="display: none">
                                            <br>
                                            <label for="inputScopeItem">Item ID</label>
                                            <div class="input-group mb-3">
                                                <input type="text" class="form-control" id="inputScopeItem" aria-describedby="button-item" onkeydown="return false;" style="caret-color: transparent !important;" >
                                                <button class="btn btn-outline-secondary class-button-item" type="button" id="button-item" data-bs-toggle="modal" data-bs-target="#itemSelect">Select</button>
                                                <div class="invalid-feedback">
                                                    Please select item.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Min money -->
                            <div class="row mb-3">
                                <label for="inputCouponMoney" class="col-sm-2 col-form-label">&#160;Min&#160;Money</label>
                                <div class="col-sm-2">
                                    <input type="number" min="0" step=".01" class="form-control" id="inputCouponMoney" required>
                                    <div class="invalid-feedback">
                                        Please provide a positive number (up to 2 digits)
                                    </div>
                                </div>
                            </div>
                            <!-- Value -->
                            <div class="row mb-3">
                                <label for="inputCouponValue" class="col-sm-2 col-form-label">&#160;Value</label>
                                <div class="col-sm-2">
                                    <input type="number" min="0" step=".01" class="form-control" id="inputCouponValue" required>
                                    <div class="invalid-feedback">
                                        Please provide a positive number (up to 2 digits)
                                    </div>
                                </div>
                            </div>
                            <!---------------- Image upload function does not implement ---------------------------
                            <div class="row rb-3" style="display: none">
                                <label for="inputCouponImage" class="col-sm-2 col-form-label">&#160;Image</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="inputCouponImage">
                                </div>
                            </div>
                             ---------------------------------------------------------------->
                            <!-- Description -->
                            <div class="row mb-3">
                                <label for="inputCouponDescription" class="col-sm-2 col-form-label">&#160;Description</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="inputCouponDescription" required></textarea>
                                    <div class="invalid-feedback">
                                        Please provide a description
                                    </div>
                                </div>
                            </div>
                            <br><br>
                            <div class="row mb-3">
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-secondary" id="create-clear-btn">Clear</button>
                                </div>
                                <div class="col-sm-5">
                                    <button type="submit" class="btn btn-primary">Create</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!----------------------------------- Show Coupon List --------------------------------------------------------------->
                    <div class="tab-pane fade" id="v-pills-list" role="tabpanel" aria-labelledby="v-pills-list-tab" tabindex="0">
                        <div class="alert alert-success" style="display:none" id="edit-success-alert">Update Successful!</div>
                        <div class="alert alert-danger" style="display:none" id="edit-falied-alert">Update Failed!(Server Side)</div>
                        <div class="alert alert-danger" style="display:none" id="delete-falied-alert">Delete Failed!(Server Side)</div>
                        <table id="coupon-list-table" class="table table-striped" style="width: 100%">
                            <caption style="caption-side: top">Coupon Lists</caption>
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Scope</th>
                                <th>Min Money</th>
                                <th>Value</th>
                                <th>Created Date</th>
                                <th>Description</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <!----------------------------------------- Unfinished features ------------------------------------------------------->
                    <div class="tab-pane fade" id="v-pills-distribution" role="tabpanel" aria-labelledby="v-pills-distribution-tab" tabindex="0">distribute</div>
                    <div class="tab-pane fade" id="v-pills-state" role="tabpanel" aria-labelledby="v-pills-state-tab" tabindex="0">state</div>
                </div>
           </div>
        </div>
    </div>
    <!----------------------------------------------------All Modals ------------------------------------------------------------------------------>

    <!-- Res Modals -->
    <div class="modal fade" id="resSelect" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="resStaticBackdropLabel" aria-hidden="true" style="z-index: 4563;">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="resStaticBackdropLabel">Select Restaurant</h1>
                    <button type="button" class="btn-close" onclick="$('#resSelect').modal('hide');" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table id="res-table" class="table table-striped multi-datatable" style="width: 100%">
                        <thead>
                        <tr>
                            <th>Restaurant ID</th>
                            <th>Restaurant Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="$('#resSelect').modal('hide');">Close</button>
                    <button type="button" class="btn btn-primary"  id="res-select-btn">Select</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Items Modals -->
    <div class="modal fade" id="itemSelect" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="itemStaticBackdropLabel" aria-hidden="true" style="z-index: 4564;">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="itemStaticBackdropLabel">Select Items</h1>
                    <button type="button" class="btn-close" onclick="$('#itemSelect').modal('hide');" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table id="items-table" class="table table-striped multi-datatable" style="width: 100%">
                        <thead>
                        <tr>
                            <th>Item ID</th>
                            <th>Item Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="$('#itemSelect').modal('hide');">Close</button>
                    <button type="button" class="btn btn-primary"  id="items-select-btn">Select</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Confirm modal-->
    <div class="modal fade" id="delete-confirm-modal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="deleteModalLabel">Warning</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure to remove the selected coupons? (Cannot be withdrawn)
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                    <button type="button" class="btn btn-primary" id="confirm-delete-btn" data-bs-dismiss="modal">Yes</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Edit modal -->
    <div class="modal fade" id="edit-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="editStaticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="editStaticBackdropLabel">Edit Coupon</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="coupon/create" method="post" class="needs-validation" novalidate id="edit-form">
                    <div class="modal-body">
                            <!-- Coupon Name -->
                            <div class="row mb-3">
                                <label for="inputCouponName2" class="col-sm-2 col-form-label">&#160;Name</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="inputCouponName2" maxlength="25" required>
                                    <div class="invalid-feedback">
                                        Please provide a name(max 25 length).
                                    </div>
                                </div>
                            </div>
                            <!-- Coupon Scope -->
                            <div class="row mb-3">
                                <label for="inputCouponScope2" class="col-sm-2 col-form-label">&#160;Scope</label>
                                <div class="col-sm-6">
                                    <select class="form-select" id="inputCouponScope2">
                                        <option selected>All store</option>
                                        <option>Specific store</option>
                                        <option>Specific items in specific store</option>
                                    </select>
                                    <!-- Restaurant option -->
                                    <div class="row mb-9" id="scope-new-row2" style="display: none">
                                        <div class="col-sm-6" id="scope-res2" style="display: none">
                                            <br>
                                            <label for="inputScopeRes2">Restaurant ID</label>
                                            <div class="input-group mb-3">
                                                <input type="text" class="form-control" id="inputScopeRes2" aria-describedby="button-res" onkeydown="return false;" style="caret-color: transparent !important;">
                                                <button class="btn btn-outline-secondary class-button-res" type="button" id="button-res2" >Select</button>
                                                <div class="invalid-feedback">
                                                    Please select restaurant.
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Item option -->
                                        <div class="col-sm-5" id="scope-item2" style="display: none">
                                            <br>
                                            <label for="inputScopeItem2">Item ID</label>
                                            <div class="input-group mb-3">
                                                <input type="text" class="form-control" id="inputScopeItem2" aria-describedby="button-item2" onkeydown="return false;" style="caret-color: transparent !important;" >
                                                <button class="btn btn-outline-secondary" type="button" id="button-item2" >Select</button>
                                                <div class="invalid-feedback">
                                                    Please select item.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Min money -->
                            <div class="row mb-3">
                                <label for="inputCouponMoney2" class="col-sm-2 col-form-label">&#160;Min&#160;Money</label>
                                <div class="col-sm-2">
                                    <input type="number" min="0" step=".01" class="form-control" id="inputCouponMoney2" required>
                                    <div class="invalid-feedback">
                                        Please provide a positive number (up to 2 digits)
                                    </div>
                                </div>
                            </div>
                            <!-- Value -->
                            <div class="row mb-3">
                                <label for="inputCouponValue2" class="col-sm-2 col-form-label">&#160;Value</label>
                                <div class="col-sm-2">
                                    <input type="number" min="0" step=".01" class="form-control" id="inputCouponValue2" required>
                                    <div class="invalid-feedback">
                                        Please provide a positive number (up to 2 digits)
                                    </div>
                                </div>
                            </div>
                            <!---------------- Image upload function does not implement ---------------------------
                            <div class="row rb-3" style="display: none">
                                <label for="inputCouponImage2" class="col-sm-2 col-form-label">&#160;Image</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="inputCouponImage2">
                                </div>
                            </div>
                             ---------------------------------------------------------------->
                            <!-- Description -->
                            <div class="row mb-3">
                                <label for="inputCouponDescription2" class="col-sm-2 col-form-label">&#160;Description</label>
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="inputCouponDescription2" required></textarea>
                                    <div class="invalid-feedback">
                                        Please provide a description
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary" id="edit-update-btn">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

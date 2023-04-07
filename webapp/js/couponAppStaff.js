// Reference from Bootstarp5 Validation----------------
function formValidation() {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
}
//cler create form content
function formClear()
{
    $("#create-form").trigger("reset");
    $("#create-form").removeClass("was-validated");
    $("#scope-new-row").css('display', 'none');
    $("#inputScopeRes").removeAttr('required');
    $("#inputScopeItem").removeAttr('required');
}
// call then coupons list button click
function showCouponsToDataTable()
{
    let dt = $("#coupon-list-table").DataTable();
    dt.clear().draw();
    $.ajax({
        type : "get",
        url : "coupon/showCoupons",
        data : "t=" + Date.parse(new Date()),
        async: true,
        success: (jsonStr)=>{
            let dt = $("#coupon-list-table").DataTable();
            let jsonObj = $.parseJSON(jsonStr)
            for (let i = 0; i < jsonObj.length; i++)
                dt.row.add([jsonObj[i].couponId, jsonObj[i].couponName, jsonObj[i].couponScope, jsonObj[i].couponMinMoney, jsonObj[i].couponValue, jsonObj[i].createdDate, jsonObj[i].couponDescription]).draw();
        }
    })
}

//--------------------------onload event------------------------------------------
$(()=>{
    //----------add form validation-----------------------------------------
    formValidation();
    //-----------------------------------datatable setting------------------------
    $(".multi-datatable").DataTable(
        {
            select: {
                style: "multi"
            },
            dom: 'Bfrtip',
            buttons: [
                'selectAll',
                'spacer',
                'selectNone',
            ]
        }
    )

    $("#coupon-list-table").DataTable(
        {
            select: {
                style: "multi"
            },
            dom: 'Bfrtip',
            buttons: [
                'selectAll',
                'spacer',
                'selectNone',
                'spacer',
                {
                    text: "Refresh",
                    className: "btn btn-info",
                    action: function (e, dt, node, config){
                        showCouponsToDataTable();
                    }
                },
                'spacer',
                {
                    text: "Edit",
                    className: "btn btn-warning",
                    action: function (e, dt, node, config){
                        if (dt.rows(".selected").count() > 1)
                            alert("You cannot edit more than one item at a time");
                        else if (dt.rows(".selected").count() == 1)
                        {
                            $("#edit-modal").modal("show");
                            $("#inputCouponName2").val(dt.rows(".selected").data()[0][1]);
                            const selectOption = $("#inputCouponScope2 option:selected");
                            switch (dt.rows(".selected").data()[0][2])
                            {
                                case 0:
                                    $("#inputCouponScope2").val("All store");
                                    $("#inputCouponScope2").trigger("change");
                                    break;
                                case 1:
                                    // Since .val() will not trigger event, manual trigger change event
                                    $("#inputCouponScope2").val("Specific store");
                                    $("#inputCouponScope2").trigger("change");
                                    $.ajax({
                                        type : "get",
                                        url : "coupon/findRes",
                                        data : "t=" + Date.parse(new Date()),
                                        async: true,
                                        success: (jsonStr)=>{
                                            let dt = $("#res-table").DataTable();
                                            let resIds = "";
                                            let jsonObj = $.parseJSON(jsonStr)
                                            for (let i = 0; i < jsonObj.length; i++)
                                                resIds += jsonObj[i].restaurantID + ",";
                                            $("#inputScopeRes2").val(resIds.substring(0, resIds.length - 1));
                                        }
                                    })
                                    break;
                                case 2:
                                    $("#inputCouponScope2").val("Specific items in specific store");
                                    $("#inputCouponScope2").trigger("change");
                                    $.ajax({
                                        type : "get",
                                        url : "coupon/findARes",
                                        data : {cId: dt.rows(".selected").data()[0][0], t: Date.parse(new Date())},
                                        async: false,
                                        success: (couponId)=>{
                                            $("#inputScopeRes2").val(couponId);
                                        }
                                    })
                                    $.ajax({
                                        type : "get",
                                        url : "coupon/findItems",
                                        data : {resId: $("#inputScopeRes2").val(), t: Date.parse(new Date())},
                                        async: false,
                                        success: (jsonStr)=>{
                                            let itemIds = "";
                                            if (jsonStr && jsonStr !== "")
                                            {
                                                let jsonObj = $.parseJSON(jsonStr)
                                                for (let i = 0; i < jsonObj.length; i++)
                                                    itemIds += jsonObj[i].itemID + ",";
                                                $("#inputScopeItem2").val(itemIds.substring(0, itemIds.length - 1));
                                            }
                                        }
                                    })
                                    break;
                                default:
                                    return;
                            }
                            $("#inputCouponMoney2").val(dt.rows(".selected").data()[0][3]);
                            $("#inputCouponValue2").val(dt.rows(".selected").data()[0][4]);
                            $("#inputCouponDescription2").val(dt.rows(".selected").data()[0][6]);
                        }
                    }
                },
                'spacer',
                {
                    text: "Delete",
                    className: "btn btn-danger",
                    action: function (e, dt, node, config){
                        if (dt.rows(".selected").count())
                            $("#delete-confirm-modal").modal("show");
                    }
                }
            ]
        }
    )
    //--------------------Register buttons click event------------------------------------------------------
    $("#page-close-btn").on('click', ()=>{
        location.href = "index.jsp";
    })

    /* abandon
    $("#v-pills-create-tab").on('click', ()=>{
        $("#create-form").removeClass('was-validated');
    });*/

    $("#res-select-btn").on('click', ()=>{
        let dt = $("#res-table").DataTable();
        let content = "";
        for (let i = 0; i < dt.rows(".selected").count(); i++)
            content += dt.rows( ".selected" ).data()[i][0] + ",";
        $("#inputScopeRes").val(content.substring(0, content.length - 1));
        $("#inputScopeRes2").val(content.substring(0, content.length - 1));
        $("#resSelect").modal("hide");
    })

    $(".class-button-res").on('click', ()=>{
        $("#resSelect").modal("show");
        const selectOption = $("#inputCouponScope option:selected");
        const selectOption2 = $("#inputCouponScope2 option:selected");
        let dt = $("#res-table").DataTable();
        if (selectOption.val() == "Specific store" || selectOption2.val() == "Specific store")
            dt.select.style("multi");
        else if (selectOption.val() == "Specific items in specific store" ||selectOption2.val() == "Specific items in specific store" )
            dt.select.style("single");
        dt.clear().draw();
        $.ajax({
            type : "get",
            url : "coupon/findRes",
            data : "t=" + Date.parse(new Date()),
            async: true,
            success: (jsonStr)=>{
                let dt = $("#res-table").DataTable();
                let jsonObj = $.parseJSON(jsonStr)
                for (let i = 0; i < jsonObj.length; i++)
                    dt.row.add([jsonObj[i].restaurantID, jsonObj[i].restaurantName]).draw();
            }
        })
    })

    $(".class-button-item").on('click', ()=>{
        let dt = $("#items-table").DataTable();
        $("#itemSelect").modal("show");
        dt.clear().draw();
        $.ajax({
            type : "get",
            url : "coupon/findItems",
            data : {resId: $("#inputScopeRes").val(), t: Date.parse(new Date())},
            async: true,
            success: (jsonStr)=>{
                let dt = $("#items-table").DataTable();
                if (jsonStr && jsonStr !== "")
                {
                    let jsonObj = $.parseJSON(jsonStr)
                    for (let i = 0; i < jsonObj.length; i++)
                        dt.row.add([jsonObj[i].itemID, jsonObj[i].description]).draw();
                }
            }
        })
    })

    $("#button-item2").on('click', ()=>{
        let dt = $("#items-table").DataTable();
        $("#itemSelect").modal("show");
        dt.clear().draw();
        $.ajax({
            type : "get",
            url : "coupon/findItems",
            data : {resId: $("#inputScopeRes2").val(), t: Date.parse(new Date())},
            async: true,
            success: (jsonStr)=>{
                let dt = $("#items-table").DataTable();
                if (jsonStr && jsonStr !== "")
                {
                    let jsonObj = $.parseJSON(jsonStr)
                    for (let i = 0; i < jsonObj.length; i++)
                        dt.row.add([jsonObj[i].itemID, jsonObj[i].description]).draw();
                }
            }
        })
    })

    $("#items-select-btn").on('click', ()=>{
        let dt = $("#items-table").DataTable();
        $("#itemSelect").modal("hide");
        let content = "";
        for (let i = 0; i < dt.rows(".selected").count(); i++)
            content += dt.rows( ".selected" ).data()[i][0] + ",";
        $("#inputScopeItem").val(content.substring(0, content.length - 1));
        $("#inputScopeItem2").val(content.substring(0, content.length - 1));
    })

    $("#create-clear-btn").on('click', formClear)

    $("#v-pills-list-tab").on('click', showCouponsToDataTable)

    $("#confirm-delete-btn").on('click', ()=>{
        let dt = $("#coupon-list-table").DataTable();
        let ids = "";
        for (let i = 0; i < dt.rows(".selected").count(); i++)
            ids += dt.rows( ".selected" ).data()[i][0] + ",";
        ids = ids.substring(0, ids.length - 1);
        $.ajax({
            type : "get",
            url : "coupon/deleteCoupon",
            data : {couponIds: ids, t: Date.parse(new Date())},
            async: false,
            success: showCouponsToDataTable,
            error: ()=> {
                $("#delete-falied-alert").fadeIn();
                $("#delete-falied-alert").fadeOut(2000);
            }
        })
    })

    //----------------Scope select text change event----------------------------------------------------------
    $("#inputCouponScope").change(()=>{
        const selectOption = $("#inputCouponScope option:selected");
        if (selectOption.val() == "Specific store")
        {
            $("#scope-new-row").css('display', '');
            $("#scope-res").css('display', '');
            $("#scope-item").css('display', 'none');
            $("#inputScopeRes").attr("required", true);
            $("#inputScopeItem").removeAttr('required');
            $("#inputScopeRes").val('');
        }
        else if (selectOption.val() == "All store")
        {
            $("#scope-new-row").css('display', 'none');
            $("#inputScopeRes").removeAttr('required');
            $("#inputScopeItem").removeAttr('required');
        }
        else if (selectOption.val() == "Specific items in specific store")
        {
            $("#scope-new-row").css('display', '');
            $("#scope-res").css('display', '');
            $("#scope-item").css('display', '');
            $("#inputScopeRes").val('');
            $("#inputScopeItem").val('');
            $("#inputScopeRes").attr('required', true);
            $("#inputScopeItem").attr('required', true);
        }
    })

    $("#inputCouponScope2").change(()=>{
        const selectOption = $("#inputCouponScope2 option:selected");
        if (selectOption.val() == "Specific store")
        {
            $("#scope-new-row2").css('display', '');
            $("#scope-res2").css('display', '');
            $("#scope-item2").css('display', 'none');
            $("#inputScopeRes2").attr("required", true);
            $("#inputScopeItem2").removeAttr('required');
            $("#inputScopeRes2").val('');
        }
        else if (selectOption.val() == "All store")
        {
            $("#scope-new-row2").css('display', 'none');
            $("#inputScopeRes2").removeAttr('required');
            $("#inputScopeItem2").removeAttr('required');
        }
        else if (selectOption.val() == "Specific items in specific store")
        {
            $("#scope-new-row2").css('display', '');
            $("#scope-res2").css('display', '');
            $("#scope-item2").css('display', '');
            $("#inputScopeRes2").val('');
            $("#inputScopeItem2").val('');
            $("#inputScopeRes2").attr('required', true);
            $("#inputScopeItem2").attr('required', true);
        }
    })
    //-----------------------------------Form submit event------------------------------
    $("#create-form").on("submit", (e)=>{
        if (document.forms["create-form"].reportValidity())
        {
            e.preventDefault();
            $.ajax({
                type : "post",
                url : "coupon/create",
                data : {
                    cName: $("#inputCouponName").val(),
                    cScope: $("#inputCouponScope").val(),
                    resId: $("#inputScopeRes").val(),
                    itemId: $("#inputScopeItem").val(),
                    minMoney: $("#inputCouponMoney").val(),
                    value: $("#inputCouponValue").val(),
                    description: $("#inputCouponDescription").val()
                },
                async: false,
                success: ()=>{
                    $("#create-success-alert").fadeIn();
                    $("#create-success-alert").fadeOut(2000);
                    formClear();
                },
                error: ()=>{
                    $("#create-falied-alert").fadeIn();
                    $("#create-falied-alert").fadeOut(2000);
                }
            })
        }
    });
    $("#edit-form").on("submit", (e)=>{
        if (document.forms["edit-form"].reportValidity())
        {
            e.preventDefault();
            $.ajax({
                type : "post",
                url : "coupon/update",
                data : {
                    cId: $("#coupon-list-table").DataTable().rows(".selected").data()[0][0],
                    cName: $("#inputCouponName2").val(),
                    cScope: $("#inputCouponScope2").val(),
                    resId: $("#inputScopeRes2").val(),
                    itemId: $("#inputScopeItem2").val(),
                    minMoney: $("#inputCouponMoney2").val(),
                    value: $("#inputCouponValue2").val(),
                    description: $("#inputCouponDescription2").val()
                },
                async: false,
                success: ()=>{
                    $("#edit-modal").modal("hide");
                    $("#edit-success-alert").fadeIn();
                    $("#edit-success-alert").fadeOut(2000);
                    showCouponsToDataTable();
                },
                error: ()=>{
                    $("#edit-falied-alert").fadeIn();
                    $("#edit-falied-alert").fadeOut(2000);
                }
            })
        }
    });
})
// testing
document.cookie =
  "access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXNzYWdlIjoiSldUIFJ1bGVzISIsImlhdCI6MTQ1OTQ0ODExOSwiZXhwIjoxNDU5NDU0NTE5fQ.-yIVBD5b73C75osbmwwshQNRC7frWUYrqaTjTpza2y4";

updateOrders();
var intervalID = setInterval(updateOrders, 500);

function updateOrders() {
  fetch("get-order", {
    method: "GET",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      Authorization: getCookie("access_token"),
    },
  })
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return response.text().then((text) => {
        throw new Error(text);
      });
    })
    .then((data) => {
      //console.log(data);

      let main = document.querySelector("main");
      while (main.firstChild) {
        main.removeChild(main.firstChild);
      }

      data.orders.forEach((order) => {
        let gridItem = document.createElement("div");
        gridItem.setAttribute("class", "grid-item");
        gridItem.setAttribute("id", order.orderID);

        let orderHeader = document.createElement("div");
        orderHeader.setAttribute("class", "order-header");
        if (order.status == "Order Received") {
          orderHeader.classList.add("order-received");
        }

        let orderCancel = document.createElement("button");
        orderCancel.setAttribute("class", "order-cancel");
        orderCancel.setAttribute(
          "onclick",
          `updateOrderByOrderID(${order.orderID}, "Cancelled");`
        );
        orderCancel.innerHTML = "Cancel";

        let orderNo = document.createElement("h3");
        orderNo.setAttribute("class", "order-no");
        orderNo.innerHTML = `Order ${order.orderID}`;

        let orderDone = document.createElement("button");
        orderDone.setAttribute("class", "order-done");
        if (order.status == "Order Received") {
          orderDone.setAttribute(
            "onclick",
            `updateOrderByOrderID(${order.orderID}, "Preparing")`
          );
          orderDone.innerHTML = "Accept";
        } else {
          orderDone.setAttribute(
            "onclick",
            `updateOrderByOrderID(${order.orderID}, "Prepared")`
          );
          orderDone.innerHTML = "Done";
        }

        orderHeader.append(orderCancel, orderNo, orderDone);

        let table = document.createElement("table");
        table.setAttribute("class", "food-list");
        order.orderItems.forEach((item) => {
          let tr = document.createElement("tr");

          let nameAndComment = document.createElement("td");
          nameAndComment.innerHTML = item.name;
          if (item.comment != "") {
            let comment = document.createElement("span");
            comment.setAttribute("class", "food-comment");
            comment.innerHTML = "&ensp;* " + item.comment;
            nameAndComment.append(document.createElement("br"), comment);
          }

          let quantity = document.createElement("td");
          quantity.innerHTML = "x" + item.quantity;

          tr.append(nameAndComment, quantity);

          table.append(tr);
        });

        let instructions = document.createElement("p");
        instructions.setAttribute("class", "instructions");
        instructions.innerHTML = order.foodInstructions;

        gridItem.append(orderHeader, table, instructions);
        main.append(gridItem);
      });
    })
    .catch((error) => {
      clearInterval(intervalID);
      console.log(error);
      alert("Something went wrong! Please try again.");
    });
}

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(";");
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

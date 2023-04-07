var driverID = document.getElementById("driver-no").getAttribute("value");

updateDeliveries();
var intervalID = setInterval(updateDeliveries, 500);

function updateDeliveries() {
  fetch("get-delivery", {
    method: "GET",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      //Authorization: getCookie("access_token"),
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
      console.log(data);

      let assigned = document.querySelector(".assigned-deliveries .body");
      while (assigned.firstChild) {
        assigned.removeChild(assigned.firstChild);
      }

      data.assignedDeliveries.forEach((delivery) => {
        var orderStatus = 0;
        switch (delivery.status) {
          case "Order Received":
            orderStatus = 0;
            break;
          case "Preparing":
            orderStatus = 1;
            break;
          case "Prepared":
            orderStatus = 2;
            break;
          case "Delivering":
            orderStatus = 3;
            break;
          case "Delivered":
            orderStatus = 4;
            break;
          default:
            orderStatus = -1;
        }

        assigned.insertAdjacentHTML(
          "beforeend",
          `<div class="section" id="${delivery.deliveryID}">
              <h1>Delivery no <span class="delivery-no">
              ${delivery.deliveryID}</span></h1>
              <div class="delivery-details">
                  <ul class="status">
                      <li>
                          <div class="check-icon">
                              <img
                                  src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                  id="order-received-img"
                                  ${orderStatus < 0 ? "class='hidden'" : ""}
                              />
                          </div>
                          <b>Order received</b>
                      </li>
                      <li>
                          <div class="check-icon">
                              <img 
                                  src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                  id="prepared-img"
                                  ${orderStatus < 2 ? "class='hidden'" : ""}
                              />
                          </div>
                          <b>Prepared</b>
                      </li>
                      <li>
                          <div class="check-icon">
                              <img 
                                  src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                  id="delivered-img"
                                  ${orderStatus < 3 ? "class='hidden'" : ""}
                              />
                          </div>
                          <b>Delivered</b>
                      </li>
                  </ul>
                  <hr />
                  <table>
                      <tr>
                          <td>Order Status:</td>
                          <td id="order-status"
                          ${
                            delivery.status == "Cancelled"
                              ? "class='warning-text'"
                              : ""
                          }>${delivery.status}</td>
                      </tr>
                      <tr>
                          <td>Street:</td>
                          <td id="delivery-street">${delivery.street}</td>
                      </tr>
                      <tr>
                          <td>Suburb:</td>
                          <td id="delivery-suburb">
                          ${delivery.suburb}</td>
                      </tr>
                      <tr>
                          <td>State:</td>
                          <td id="delivery-state">
                          ${delivery.state}</td>
                      </tr>
                      <tr>
                          <td>Postal:</td>
                          <td id="delivery-postal">
                          ${delivery.postal}</td>
                      </tr>
                      <tr>
                        <td>Instruction:</td>
                        <td id="driver-instructions">
                        ${
                          delivery.instruction != null
                            ? delivery.instruction
                            : ""
                        }</td>
                      </tr>
                  </table>
                  <hr />
                  <div class="actions">
                      <button class="cancel-button" value=${
                        delivery.deliveryID
                      }>Cancel</button>
                      <button class="done-button" 
                      value=${delivery.deliveryID} 
                      ${orderStatus < 2 ? "disabled" : ""}>Done</button>
                  </div>
              </div>
          </div>`
        );
      });

      let available = document.querySelector(".available-deliveries .body");
      while (available.firstChild) {
        available.removeChild(available.firstChild);
      }

      data.availableDeliveries.forEach((delivery) => {
        var orderStatus = 0;
        switch (delivery.status) {
          case "Order Received":
            orderStatus = 0;
            break;
          case "Preparing":
            orderStatus = 1;
            break;
          case "Prepared":
            orderStatus = 2;
            break;
          case "Delivering":
            orderStatus = 3;
            break;
          case "Delivered":
            orderStatus = 4;
            break;
          default:
            orderStatus = -1;
        }

        available.insertAdjacentHTML(
          "beforeend",
          `<div class="section" id="${delivery.deliveryID}">
              <h1>Delivery no <span class="delivery-no">
              ${delivery.deliveryID}</span></h1>
              <div class="delivery-details">
                  <ul class="status">
                      <li>
                          <div class="check-icon">
                              <img
                                  src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                  id="order-received-img"
                                  ${orderStatus < 0 ? "class='hidden'" : ""}
                              />
                          </div>
                          <b>Order received</b>
                      </li>
                      <li>
                          <div class="check-icon">
                              <img 
                                  src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                  id="prepared-img"
                                  ${orderStatus < 2 ? "class='hidden'" : ""}
                              />
                          </div>
                          <b>Prepared</b>
                      </li>
                      <li>
                          <div class="check-icon">
                              <img 
                                  src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                  id="delivered-img"
                                  ${orderStatus < 3 ? "class='hidden'" : ""}
                              />
                          </div>
                          <b>Delivered</b>
                      </li>
                  </ul>
                  <hr />
                  <table>
                      <tr>
                          <td>Order Status:</td>
                          <td id="order-status" 
                          ${
                            delivery.status == "Cancelled"
                              ? "class='warning-text'"
                              : ""
                          }>${delivery.status}</td>
                      </tr>
                      <tr>
                          <td>Street:</td>
                          <td id="delivery-street">${delivery.street}</td>
                      </tr>
                      <tr>
                          <td>Suburb:</td>
                          <td id="delivery-suburb">
                          ${delivery.suburb}</td>
                      </tr>
                      <tr>
                          <td>State:</td>
                          <td id="delivery-state">
                          ${delivery.state}</td>
                      </tr>
                      <tr>
                          <td>Postal:</td>
                          <td id="delivery-postal">
                          ${delivery.postal}</td>
                      </tr>
                      <tr>
                        <td>Instruction:</td>
                        <td id="driver-instructions">
                        ${
                          delivery.instruction != null
                            ? delivery.instruction
                            : ""
                        }</td>
                      </tr>
                  </table>
                  <hr />
                  <div class="actions">
                      <button class="accept-button" value=${
                        delivery.deliveryID
                      }>Accept</button>
                  </div>
              </div>
          </div>`
        );
      });
    })
    .then(() => {
      document.querySelectorAll(".cancel-button").forEach((button) =>
        button.addEventListener("click", function () {
          updateDelivery(button.getAttribute("value"), "Cancel");
        })
      );

      document.querySelectorAll(".done-button").forEach((button) =>
        button.addEventListener("click", function () {
          updateOrderByDeliveryID(button.getAttribute("value"), "Delivered");
        })
      );

      document.querySelectorAll(".accept-button").forEach((button) =>
        button.addEventListener("click", function () {
          updateDelivery(button.getAttribute("value"), driverID);
        })
      );
    })
    .catch((error) => {
      clearInterval(intervalID);
      console.log(error);
      alert("Something went wrong! Please try again.");
    });
}

// function getCookie(cname) {
//   let name = cname + "=";
//   let decodedCookie = decodeURIComponent(document.cookie);
//   let ca = decodedCookie.split(";");
//   for (let i = 0; i < ca.length; i++) {
//     let c = ca[i];
//     while (c.charAt(0) == " ") {
//       c = c.substring(1);
//     }
//     if (c.indexOf(name) == 0) {
//       return c.substring(name.length, c.length);
//     }
//   }
//   return "";
// }

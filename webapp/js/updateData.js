function updateOrderByOrderID(orderID, status) {
  if (status === "Cancel") {
    if (!confirm(`Do you want to cancel order ${orderID}?`)) {
      return;
    }
  }
  let data = {
    orderID: orderID,
    status: status,
  };
  sendData("update-order", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      //Authorization: getCookie("access_token"),
    },
    body: convertData(data),
  });
}

function updateOrderByDeliveryID(deliveryID, status) {
  if (status === "Cancel") {
    if (!confirm(`Do you want to cancel delivery ${deliveryID}?`)) {
      return;
    }
  }
  let data = {
    deliveryID: deliveryID,
    status: status,
  };
  sendData("update-order", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      //Authorization: getCookie("access_token"),
    },
    body: convertData(data),
  });
}

function updateDelivery(deliveryID, driverID) {
  if (driverID === "Cancel") {
    if (!confirm(`Do you want to cancel delivery ${deliveryID}?`)) {
      return;
    }
    driverID = null;
  }
  let data = {
    deliveryID: deliveryID,
    driverID: driverID,
  };
  sendData("update-delivery", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      //Authorization: getCookie("access_token"),
    },
    body: convertData(data),
  });
}

function deleteDelivery(deliveryID) {
  if (!confirm(`Do you want to delete delivery ${deliveryID}?`)) {
    return;
  }
  let data = {
    deliveryID: deliveryID,
  };
  sendData("delete-delivery", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
      //Authorization: getCookie("access_token"),
    },
    body: convertData(data),
  });
}

function sendData(api, options) {
  fetch(api, options)
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return response.text().then((text) => {
        throw new Error(text);
      });
    })
    .then((data) => {
      alert(data);
    })
    .catch((error) => {
      console.log(error);
      alert("Something went wrong! Please try again.");
    });
}

function convertData(data) {
  var formBody = [];
  for (var property in data) {
    var encodedKey = encodeURIComponent(property);
    var encodedValue = encodeURIComponent(data[property]);
    formBody.push(encodedKey + "=" + encodedValue);
  }
  formBody = formBody.join("&");
  return formBody;
}

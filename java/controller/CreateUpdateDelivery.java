package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Order;
import model.Delivery;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/CreateUpdateDelivery", value = "/create-update-delivery")
public class CreateUpdateDelivery extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        clearErrSession(session);

        String orderType = request.getParameter("order-type");

        Order order = null;
        if (validator.isNumeric(request.getParameter("orderID"))) {
            order = manager.getOrder(Integer.parseInt(request.getParameter("orderID")));
        }

        if (order == null) {
            session.setAttribute("orderErr", "Missing or wrong order");
            request.getRequestDispatcher("createUpdateDelivery.jsp").include(request, response);
            return;
        }

        if (order.getStatus().equals("Delivered")) {
            session.setAttribute("orderErr", "Order delivered");
            request.getRequestDispatcher("createUpdateDelivery.jsp").include(request, response);
            return;
        }

        if (order.getStatus().equals("Delivering")) {
            session.setAttribute("orderErr", "Order delivering");
            request.getRequestDispatcher("createUpdateDelivery.jsp").include(request, response);
            return;
        }

        order.setOrderType(orderType);

        boolean passed = true;

        if (orderType.equals("Delivery")) {
            String street = request.getParameter("street");
            if (validator.isBlankOrNull(street)) {
                session.setAttribute("streetErr", "Invalid street");
                passed = false;
            }
            String suburb = request.getParameter("suburb");
            if (validator.isBlankOrNull(suburb)) {
                session.setAttribute("suburbErr", "Invalid suburb");
                passed = false;
            }
            String state = request.getParameter("state");
            if (!validator.validateState(state)) {
                session.setAttribute("stateErr", "Invalid State, use state code with capital letters");
                passed = false;
            }
            String postal = request.getParameter("postal");
            if (!validator.validatePostCode(postal)) {
                session.setAttribute("postalErr", "Invalid Postal Code, 4 digits");
                passed = false;
            }
            String instructions = request.getParameter("instructions");
            double fee = 55.55;

            if (passed) {
                if (manager.getDeliveryByOrderID(order.getOrderID()) != null) {
                    Delivery delivery = manager.getDeliveryByOrderID(order.getOrderID());
                    delivery.setOrderID(order.getOrderID());
                    delivery.setDeliveryStreet(street);
                    delivery.setDeliverySuburb(suburb);
                    delivery.setDeliveryState(state);
                    delivery.setDeliveryPostal(postal);
                    delivery.setDeliveryFee(fee);
                    delivery.setDriverInstructions(instructions);
                    manager.updateDelivery(delivery);
                } else {
                    Delivery delivery = new Delivery(order.getOrderID(), street, suburb, state, postal, fee,
                            instructions);
                    manager.createDelivery(delivery);
                }
            } else {
                request.getRequestDispatcher("createUpdateDelivery.jsp").include(request, response);
                return;
            }
        }
        Delivery delivery = manager.getDeliveryByOrderID(order.getOrderID());
        request.setAttribute("deliveryID", delivery.getDeliveryID());
        request.getRequestDispatcher("deliveryStatus.jsp").include(request, response);
    }

    private void clearErrSession(HttpSession session) {
        session.removeAttribute("orderErr");
        session.removeAttribute("stateErr");
        session.removeAttribute("postalErr");
    }

    private void createManager(HttpServletRequest request, HttpServletResponse response) {
        DBConnector db;
        DBManager manager;
        Connection conn;

        try {
            db = new DBConnector();
            HttpSession session = request.getSession();

            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}

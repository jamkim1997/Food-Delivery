package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.*;
import model.Delivery;
import model.Order;

import java.sql.Connection;

@WebServlet(name = "controller/UpdateReview", value = "/update-review")
public class UpdateReview extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();

        Order order = null;
        if (validator.isNumeric(request.getParameter("orderID"))) {
            order = manager.getOrder(Integer.parseInt(request.getParameter("orderID")));
        }

        if (order == null) {
            session.setAttribute("reviewErr", "Order not found");
            request.getRequestDispatcher("customerReview.jsp").include(request, response);
            return;
        }

        if (validator.isNumeric(request.getParameter("order-rate"))) {
            order.setFoodRating(Integer.parseInt(request.getParameter("order-rate")));
        }
        order.setFoodFeedback(request.getParameter("order-feedback"));

        if (!manager.updateOrder(order)) {
            session.setAttribute("reviewErr", "Something went wrong. Couldn't update Order");
            request.getRequestDispatcher("customerReview.jsp").include(request, response);
            return;
        }

        Delivery delivery = null;
        if (validator.isNumeric(request.getParameter("deliveryID"))) {
            delivery = manager.getDelivery(Integer.parseInt(request.getParameter("deliveryID")));
        }

        if (delivery != null) {
            if (validator.isNumeric(request.getParameter("delivery-rate"))) {
                delivery.setDriverRating(Integer.parseInt(request.getParameter("delivery-rate")));
            }
            delivery.setDriverFeedback(request.getParameter("delivery-feedback"));
            if (!manager.updateDelivery(delivery)) {
                session.setAttribute("reviewErr", "Something went wrong. Couldn't update Delivery");
                request.getRequestDispatcher("customerReview.jsp").include(request, response);
                return;
            }
        }

        session.setAttribute("reviewErr", "Update successful");
        request.setAttribute("orderID", request.getParameter("orderID"));
        request.setAttribute("deliveryID", request.getParameter("deliveryID"));
        request.getRequestDispatcher("customerReview.jsp").include(request, response);
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

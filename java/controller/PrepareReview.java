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

@WebServlet(name = "controller/PrepareReview", value = "/prepare-review")
public class PrepareReview extends HttpServlet {
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
        Delivery delivery = null;
        if (validator.isNumeric(request.getParameter("deliveryID"))) {
            delivery = manager.getDelivery(Integer.parseInt(request.getParameter("deliveryID")));
        }

        if (delivery == null) {
            if (validator.isNumeric(request.getParameter("orderID"))) {
                order = manager.getOrder(Integer.parseInt(request.getParameter("orderID")));
            }
        } else {
            order = manager.getOrder(delivery.getOrderID());
        }

        request.setAttribute("deliveryID", (delivery == null ? 0 : delivery.getDeliveryID()));
        request.setAttribute("orderID", order.getOrderID());
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

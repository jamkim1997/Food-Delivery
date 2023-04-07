package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.*;
import model.Delivery;
import model.Order;
import model.Staff;

import java.sql.Connection;

@WebServlet(name = "controller/UpdateOrder", value = "/update-order")
public class UpdateOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();

        // String auth = request.getHeader("Authorization");
        // Staff staff = (Staff) session.getAttribute("staff" + auth);
        // if (staff == null) {
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User");
        // return;
        // }

        Order order = null;
        if (validator.isNumeric(request.getParameter("orderID"))) {
            order = manager.getOrder(Integer.parseInt(request.getParameter("orderID")));
        }

        if (order == null) {
            if (validator.isNumeric(request.getParameter("deliveryID"))) {
                Delivery delivery = manager.getDelivery(Integer.parseInt(request.getParameter("deliveryID")));
                order = manager.getOrder(delivery.getOrderID());
            }
        }

        if (order == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found!");
            return;
        }

        if (order.getStatus().equals("Delivered")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order delivered!");
            return;
        }

        try {
            if (validator.isNumeric(request.getParameter("customerID"))) {
                order.setCustomerID(Integer.parseInt(request.getParameter("customerID")));
            }
            if (validator.isNumeric(request.getParameter("restaurantID"))) {
                order.setRestaurantID(Integer.parseInt(request.getParameter("restaurantID")));
            }
            if (request.getParameter("orderType") != null) {
                order.setOrderType(request.getParameter("orderType"));
            }
            if (validator.isNumeric(request.getParameter("couponID"))) {
                order.setCouponID(Integer.parseInt(request.getParameter("couponID")));
            }
            if (request.getParameter("status") != null) {
                order.setStatus(request.getParameter("status"));
            }
            if (validator.isNumeric(request.getParameter("foodRating"))) {
                order.setFoodRating(Integer.parseInt(request.getParameter("foodRating")));
            }
            if (request.getParameter("foodInstructions") != null) {
                order.setFoodInstructions(request.getParameter("foodInstructions"));
            }
            if (request.getParameter("foodFeedback") != null) {
                order.setFoodFeedback(request.getParameter("foodFeedback"));
            }

            String message = "";
            if (manager.updateOrder(order)) {
                message = "Updated Order " + order.getOrderID() + " Successfully!";
            } else {
                message = "Failed to delete Order " + order.getOrderID();
            }
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
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

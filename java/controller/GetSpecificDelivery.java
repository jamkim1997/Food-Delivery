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
import model.User;
import model.DeliveryDriver;
import model.Order;
import model.Delivery;
import java.sql.Connection;

@WebServlet(name = "controller/GetSpecificDelivery", value = "/get-specific-delivery")
public class GetSpecificDelivery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();

        Delivery delivery = null;

        if (validator.isNumeric(request.getParameter("deliveryID"))) {
            delivery = manager.getDelivery(Integer.parseInt(request.getParameter("deliveryID")));
        }

        if (delivery == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Delivery not found!");
            return;
        }

        Order order = manager.getOrder(delivery.getOrderID());

        Data data = new Data(order, delivery);

        String json = new Gson()
                .toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    private class Data {
        private String status;
        private String type;
        private String street;
        private String suburb;
        private String state;
        private String postal;
        private String instructions;

        private Data(Order order, Delivery delivery) {
            this.status = order.getStatus();
            this.type = order.getOrderType();
            this.street = delivery.getDeliveryStreet();
            this.suburb = delivery.getDeliverySuburb();
            this.state = delivery.getDeliveryState();
            this.postal = delivery.getDeliveryPostal();
            this.instructions = delivery.getDriverInstructions();
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

    private DeliveryDriver findDriver(HttpSession session) {
        return (DeliveryDriver) session.getAttribute("driver");
    }

    private User findUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
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

import model.Delivery;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/UpdateDelivery", value = "/update-delivery")
public class UpdateDelivery extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
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

        try {
            if (validator.isNumeric(request.getParameter("driverID"))) {
                delivery.setDriverID(Integer.parseInt(request.getParameter("driverID")));
            } else if (request.getParameter("driverID").equalsIgnoreCase("null")) {
                delivery.setDriverID(0);
            }

            String message = "";
            if (manager.updateDelivery(delivery)) {
                message = "Updated Delivery " + delivery.getDeliveryID() + " Successfully!";
            } else {
                message = "Failed to update Delivery " + delivery.getDeliveryID();
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

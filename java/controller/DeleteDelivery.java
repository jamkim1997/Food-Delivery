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
import java.sql.Connection;

@WebServlet(name = "controller/DeleteDelivery", value = "/delete-delivery")
public class DeleteDelivery extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        String message = "";
        if (manager.deleteDelivery(delivery.getDeliveryID())) {
            message = "Deleted Delivery " + delivery.getDeliveryID() + " Successfully!";
        } else {
            message = "Failed to delete Delivery " + delivery.getDeliveryID();
        }
        String json = new Gson().toJson(message);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
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

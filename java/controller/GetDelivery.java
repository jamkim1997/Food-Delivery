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
import model.OrderItem;
import model.Delivery;
import model.DeliveryDriver;
import model.MenuItem;
import model.Order;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "controller/GetDelivery", value = "/get-delivery")
public class GetDelivery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");

        DeliveryDriver driver = (DeliveryDriver) session.getAttribute("Driver");

        try {
            ArrayList<Delivery> assignedDeliveries = manager.getDeliveriesByDriverID(driver.getDriverID());
            ArrayList<Delivery> availableDeliveries = manager.getAvailableDeliveries();

            Data data = new Data(assignedDeliveries, availableDeliveries, manager);

            String json = new Gson().toJson(data);
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

    private class Data {
        private ArrayList<ModifiedDelivery> assignedDeliveries;
        private ArrayList<ModifiedDelivery> availableDeliveries;

        private Data(ArrayList<Delivery> assignedDeliveries,
                ArrayList<Delivery> availableDeliveries, DBManager manager) {
            this.assignedDeliveries = new ArrayList<ModifiedDelivery>();
            for (Delivery delivery : assignedDeliveries) {
                String status = manager.getOrder(delivery.getOrderID()).getStatus();
                if (!status.equals("Delivered")) {
                    this.assignedDeliveries.add(new ModifiedDelivery(delivery, status));
                }
            }

            this.availableDeliveries = new ArrayList<ModifiedDelivery>();
            for (Delivery delivery : availableDeliveries) {
                String status = manager.getOrder(delivery.getOrderID()).getStatus();
                if (!status.equals("Delivered")) {
                    this.availableDeliveries.add(new ModifiedDelivery(delivery, status));
                }
            }
        }

        private class ModifiedDelivery {
            private int deliveryID;
            private String status;
            private String street;
            private String suburb;
            private String state;
            private String postal;
            private String instruction;

            public ModifiedDelivery(Delivery delivery, String status) {
                this.deliveryID = delivery.getDeliveryID();
                this.status = status;
                this.street = delivery.getDeliveryStreet();
                this.suburb = delivery.getDeliverySuburb();
                this.state = delivery.getDeliveryState();
                this.postal = delivery.getDeliveryPostal();
                this.instruction = delivery.getDriverInstructions();
            }
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

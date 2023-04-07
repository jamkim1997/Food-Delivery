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
import model.Staff;
import model.OrderItem;
import model.MenuItem;
import model.Order;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "controller/GetOrder", value = "/get-order")
public class GetOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");

        // String auth = request.getHeader("Authorization");
        // Staff staff = (Staff) session.getAttribute("staff" + auth);
        // if (staff == null) {
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User!
        // Please try to login again");
        // return;
        // }

        Staff staff = (Staff) session.getAttribute("Staff");

        try {
            ArrayList<Order> orders = manager.getOrdersByResID(staff.getRestaurantID());

            Data data = new Data(orders, manager);

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
        private ArrayList<ModifiedOrder> orders;

        private Data(ArrayList<Order> orderCollection, DBManager manager) {
            orders = new ArrayList<ModifiedOrder>();
            for (Order order : orderCollection) {
                orders.add(new ModifiedOrder(order, manager));
            }
        }

        private class ModifiedOrder {
            private int orderID;
            private String orderType;
            private String status;
            private String foodInstructions;
            private ArrayList<ModifiedOrderItem> orderItems;

            private ModifiedOrder(Order order, DBManager manager) {
                this.orderID = order.getOrderID();
                this.orderType = order.getOrderType();
                this.status = order.getStatus();
                this.foodInstructions = order.getFoodInstructions();
                this.orderItems = new ArrayList<ModifiedOrderItem>();
                for (OrderItem orderItem : manager.getOrderItems(orderID)) {
                    MenuItem menuItem = manager.getMenuItem(orderItem.getItemID());
                    this.orderItems.add(new ModifiedOrderItem(menuItem.getDescription(), orderItem.getQuantity(),
                            orderItem.getComment()));
                }
            }

            private class ModifiedOrderItem {
                private String name;
                private int quantity;
                private String comment;

                public ModifiedOrderItem(String name, int quantity, String comment) {
                    this.name = name;
                    this.quantity = quantity;
                    this.comment = comment;
                }
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

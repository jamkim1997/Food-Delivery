package controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import java.util.logging.Logger;

import dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/AddMenuItem", value = "/add-menuItem")
public class AddMenuItem extends HttpServlet {
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("manager") == null){
            test(request, response);
        }

        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            // ArrayList<Restaurant> restaraunts = manager.fectRestaraunt();
            // session.setAttribute("restaraunts", restaraunts);
            // request.getRequestDispatcher("showCIM.jsp").include(request, response);
            Order order = (Order) session.getAttribute("order");            
            if(order == null){
                System.out.println("Order is null");
                session.setAttribute("orderErr", "Missing or wrong order");
                request.getRequestDispatcher("menu.jsp").include(request, response);
                return;
            }

            System.out.println("hello ");
            //System.out.println(order.getOrderID());
            session.setAttribute("order", order);
            //System.out.println(order.getStatus());
            int id = Integer.parseInt(request.getParameter("MenuItemID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity <= 0) {
                session.setAttribute("orderErr", "Invalid Quantity");
                request.getRequestDispatcher("menu.jsp").include(request, response);
                return;
            }
            String comment = request.getParameter("comment");
            OrderItem orderItem = manager.createOrderItem(order.getOrderID(), id, quantity, comment);

            //ArrayList<OrderItem> orderItems = manager.fectOrderItem(order.getOrderID());
            //session.setAttribute("order", order.getOrderID());
            //session.setAttribute("orderItem", orderItem);
            
            request.getRequestDispatcher("menu.jsp").include(request, response);
        } 
        catch (Exception ex) {
            Logger.getLogger(ShowRestaraunts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void test(HttpServletRequest request, HttpServletResponse response) {
        DBConnector db;
        DBManager manager;
        Connection conn;

        try {
            db = new DBConnector();
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }

}


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
import model.Order;
import model.OrderItem;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/DeleteOrder", value = "/delete-order")
public class DeleteOrder extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("manager") == null){
            test(request, response);
        }

        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            Order order = (Order) session.getAttribute("order");            
            if(order == null){
                System.out.println("Order is null - Remove");
            }
            System.out.println(order.getOrderID());
            manager.removeOrder(order.getOrderID());
            request.getRequestDispatcher("order.jsp").include(request, response);
            
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


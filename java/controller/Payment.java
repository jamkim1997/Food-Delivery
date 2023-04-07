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
import model.Receipt;
import dao.*;
import java.sql.Connection;
@WebServlet(name = "controller/Payment", value = "/payment")

public class Payment extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("manager") == null){
            test(request, response);
        }

        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            Order order = (Order) session.getAttribute("order");
            
            //OrderItem orderItem = (OrderItem) session.getAttribute("orderItem");
            ArrayList<Receipt> receipt = manager.fectReceipt(order.getOrderID());
            session.setAttribute("receipt", receipt);

            request.getRequestDispatcher("payment.jsp").include(request, response);
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
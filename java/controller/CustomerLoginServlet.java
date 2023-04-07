package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DBConnector;
import dao.DBManager;
import model.Customer;

@WebServlet(name = "CustomerLoginServlet", value = "/CustomerLoginServlet")

public class CustomerLoginServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        DBConnector db;
        DBManager manager;
        Connection conn;
    
        try {
            db = new DBConnector();
            response.setContentType("text/html;charset=UTF-8");
    
            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
    
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }

        manager = (DBManager) session.getAttribute("manager");

        try {
            if (manager.findCustomer(email, password) != null) {
                System.out.println("Customer Found");
                Customer customer = manager.findCustomer(email, password);
                session.setAttribute("Customer", customer);              
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else {
                System.out.println("Customer Not Found");
                request.setAttribute("Fail", "Email or Password Incorrect");
                request.getRequestDispatcher("customerLogin.jsp").include(request, response);
            }
            
            
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("nullptr exception");
            request.setAttribute("Fail", "Null Pointer Exception, Please Try Again");
            request.getRequestDispatcher("customerLogin.jsp").include(request, response);
        }
        catch (SQLException ex) {
            System.out.println("sql exception");
            ex.printStackTrace();
            request.setAttribute("Fail", "SQL Exception. Customer Not Found");
            request.getRequestDispatcher("customerLogin.jsp").include(request, response);
        }
    }

}
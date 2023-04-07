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
import model.Staff;


@WebServlet(name = "StaffLoginServlet", value = "/StaffLoginServlet")

public class StaffLoginServlet extends HttpServlet{

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
            if (manager.findUser(email, password) != null) {
                Staff staff = manager.findStaff(email, password);
                session.setAttribute("Staff", staff);  
                System.out.println(staff.getFname() + " is the first name from session staff")            ;
                request.getRequestDispatcher("index").include(request, response);
            }
            else {
                System.out.println("Customer Not Found");
                request.setAttribute("Fail", "Email or Password Incorrect");
                request.getRequestDispatcher("staffLogin.jsp").include(request, response);
            }
              
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("nullptr exception");
            request.setAttribute("Fail", "Email or Password Incorrect");
        }
        catch (SQLException ex) {
            System.out.println("sql exception");
            ex.printStackTrace();
            request.setAttribute("Fail", "Email or Password Incorrect");
        }
    }

}
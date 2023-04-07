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
import model.User;
import model.Staff;

@WebServlet(name = "StaffAddDetailsServlet", value = "/StaffAddDetailsServlet")

public class StaffAddDetailsServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Staff");

        String restaurantID = request.getParameter("restaurantID");
        String privilege = request.getParameter("privilege");
        String position = request.getParameter("position");

        int restaurantIDTemp = 0;
        int privilegeTemp = 0;

        try{
            restaurantIDTemp = Integer.parseInt(restaurantID);
            privilegeTemp = Integer.parseInt(privilege);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        
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
            System.out.println("MANAGER FAILED SOMEHOW");
            System.out.println("Exception is: " + e);
            e.printStackTrace();
        }

        manager = (DBManager) session.getAttribute("manager");

        try {

            System.out.println("Trying to add staff details");
            manager.addStaffDetails(user.getEmail(), restaurantIDTemp, privilegeTemp, position);
            Staff staff = manager.findStaff(user.getUserID());
            session.setAttribute("Staff", staff);
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("nullptr exception");
        }    
        catch (SQLException ex) {
            System.out.println("sql exception");
            request.setAttribute("Error", "Restaurant ID Does not exist");
            request.getRequestDispatcher("staffAddDetails.jsp").include(request, response);
            ex.printStackTrace();
        }
    }

}
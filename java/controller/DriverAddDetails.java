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
import model.DeliveryDriver;

@WebServlet(name = "DriverAddDetails", value = "/DriverAddDetails")

public class DriverAddDetails extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        String numberPlate = request.getParameter("numberPlate");
        String vehicleDescription = request.getParameter("vehicleDescription");
        String dAccountName = request.getParameter("dAccountName");
        // change cardPin to int
        int dAccountNumber = 0;
        int dBSB = 0;
        String dBSBTemp = request.getParameter("dBSB");
        String dAccountNumberTemp = request.getParameter("dAccountNumber");
        

        boolean numberException = false;

        try{
            dAccountNumber = Integer.parseInt(dAccountNumberTemp);
            dBSB = Integer.parseInt(dBSBTemp);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            request.setAttribute("Error", "Account Number must be an Integer");
            request.getRequestDispatcher("driverAddDetails.jsp").include(request, response);
            numberException = true;
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
        if (!numberException) {
            try {
                System.out.println("Trying to add Payment details ");
                manager.addDriverDetails(user.getUserID(), numberPlate, vehicleDescription, dAccountName, dBSB, dAccountNumber);
                DeliveryDriver driver = manager.findDriver(user.getUserID());
                session.setAttribute("Driver", driver);
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            catch (NullPointerException ex) {
                ex.printStackTrace();
                System.out.println("nullptr exception");
                request.setAttribute("Error", "Null Pointer Exception Error. Please Try Again");
                request.getRequestDispatcher("customerAddPayment.jsp").include(request, response);
            }
            catch (SQLException ex) {
                System.out.println("sql exception");
                ex.printStackTrace();
                request.setAttribute("Error", "SQL Error. Please Try Again");
                request.getRequestDispatcher("customerAddPayment.jsp").include(request, response);
            }
        }
    }

}
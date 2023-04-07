package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

@WebServlet(name = "StaffEditDetails", value = "/StaffEditDetails")

public class StaffEditDetails extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int phoneNumber = 0;
        int streetNumber = 0;
        int postcode = 0;
        int restaurantID = 0;
        int privilege = 0;
        
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String phoneTemp = request.getParameter("phone");
        String dob = request.getParameter("dob"); 

        String streetNumberTemp = request.getParameter("streetNumber");
        String streetName = request.getParameter("streetName");
        String postcodeTemp = request.getParameter("postcode");
        String state = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        String country = request.getParameter("country");

        String restaurantIDTemp = request.getParameter("restaurantID");
        String PrivilegeTemp = request.getParameter("privilege");
        String position = request.getParameter("position");

 //       LocalDate cardExpiration = LocalDate.parse(cardExpirationTemp);

        try{
            phoneNumber = Integer.parseInt(phoneTemp);
            streetNumber = Integer.parseInt(streetNumberTemp);
            postcode = Integer.parseInt(postcodeTemp);
            restaurantID = Integer.parseInt(restaurantIDTemp);
            privilege = Integer.parseInt(PrivilegeTemp);
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
            System.out.println("Trying to add Customer");
            Staff staff = (Staff) session.getAttribute("Staff");
            manager.updateStaff(staff.getUserID(), firstName, lastName, password, email, phoneNumber, dob, streetNumber, streetName, postcode, state, suburb, country, true, staff.getStaffID(), restaurantID, privilege, position);
            staff = manager.findStaff(staff.getUserID());
            session.setAttribute("Staff", staff);
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("nullptr exception");
            request.setAttribute("Error", "Null Pointer Exception. Please Try Again");
            request.getRequestDispatcher("customerRegister.jsp").include(request, response);
        }
        catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Dpublicate Email used");
            request.setAttribute("Error", "Email already in use");
            request.getRequestDispatcher("customerRegister.jsp").include(request, response);
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            System.out.println("SQL Exception");
            request.setAttribute("Error", "Restaurant ID Does not exist");
            request.getRequestDispatcher("staffEditDetails.jsp").include(request, response);
            ex.printStackTrace();
        }
    }

}
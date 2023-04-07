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

@WebServlet(name = "StaffRegisterServlet", value = "/StaffRegisterServlet")

public class StaffRegisterServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        int phone = 0;
        int streetNumber = 0;
        int postcode = 0;

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String Sphone = request.getParameter("phone");
        String dob = request.getParameter("dob"); 

        String SstreetNumber = request.getParameter("streetNumber");
        String streetName = request.getParameter("streetName");
        String Spostcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        String country = request.getParameter("country");
        
        DBConnector db;
        DBManager manager;
        Connection conn;

        try{
            phone = Integer.parseInt(Sphone);
            streetNumber = Integer.parseInt(SstreetNumber);
            postcode = Integer.parseInt(Spostcode);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    
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
            System.out.println("Trying to add staff");
            manager.addUser(firstName, lastName, password, email, phone, dob, streetNumber, streetName, postcode, state, suburb, country, true);
            System.out.println("staff entered Successful");
            session.setAttribute("email", email);
            session.setAttribute("Staff", manager.findUser(email, password));
            request.getRequestDispatcher("staffAddDetails.jsp").include(request, response);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("nullptr exception");
        }
        catch (SQLException ex) {
            System.out.println("sql exception");
                request.setAttribute("Error", "Email already in use");
                request.getRequestDispatcher("staffRegister.jsp").include(request, response);
                ex.printStackTrace();
        }
    }

}
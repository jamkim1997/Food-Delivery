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

@WebServlet(name = "CustomerRegisterServlet", value = "/CustomerRegisterServlet")

public class CustomerRegisterServlet extends HttpServlet{

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
        String Cphone = request.getParameter("phone");
        String dob = request.getParameter("dob"); 

        String CstreetNumber = request.getParameter("streetNumber");
        String streetName = request.getParameter("streetName");
        String Cpostcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        String country = request.getParameter("country");

        boolean numberException = false;

        try{
            phone = Integer.parseInt(Cphone);
            streetNumber = Integer.parseInt(CstreetNumber);
            postcode = Integer.parseInt(Cpostcode);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            request.setAttribute("Error", "Phone, Street Number and Postcode must be integers");
            request.getRequestDispatcher("customerRegister.jsp").include(request, response);
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
                System.out.println("Trying to add Customer");
                manager.addUser(firstName, lastName, password, email, phone, dob, streetNumber, streetName, postcode, state, suburb, country, true);
                System.out.println("User entered Successful");
                session.setAttribute("User", manager.findUser(email, password));
                request.getRequestDispatcher("customerAddPayment.jsp").include(request, response);
            }
            catch (NullPointerException ex) {
                ex.printStackTrace();
                System.out.println("nullptr exception");
                request.setAttribute("Error", "Null Pointer Exception. Please Try Again");
                request.getRequestDispatcher("customerRegister.jsp").include(request, response);
            }
            catch (SQLException ex) {
                System.out.println("sql exception");
                request.setAttribute("Error", "Email already in use");
                request.getRequestDispatcher("customerRegister.jsp").include(request, response);
                ex.printStackTrace();
            }
        }
    }

}
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
import model.User;

@WebServlet(name = "CustomerAddPaymentServlet", value = "/CustomerAddPaymentServlet")

public class CustomerAddPaymentServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        String cardNumberTemp = request.getParameter("cardNumber");
        String cardExpiration = request.getParameter("cardExpiration");
        int cardNumber = 0;
        // change cardPin to int
        int cardPin = 0;
        String cardPinTemp = request.getParameter("cardPin");
        String cardName = request.getParameter("cardName");

        boolean numberException = false;

        try{
            cardPin = Integer.parseInt(cardPinTemp);
            cardNumber = Integer.parseInt(cardNumberTemp);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            request.setAttribute("Error", "Card Number and Card Pin must be Integers");
            request.getRequestDispatcher("customerAddPayment.jsp").include(request, response);
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
                manager.addPaymentDetails(user.getUserID(), cardNumber, cardExpiration, cardPin, cardName);
                Customer customer = manager.findCustomer(user.getUserID());
                session.setAttribute("Customer", customer);
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
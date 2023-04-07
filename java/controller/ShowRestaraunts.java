package controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import dao.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Restaurant;

@WebServlet(name = "controller/ShowRestaraunts", value = "/show-restaraunt")
public class ShowRestaraunts extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = (HttpSession) request.getSession();

        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            ArrayList<Restaurant> restaraunts = manager.fectRestaraunt();
            session.setAttribute("restaraunts", restaraunts);
            //session.getAttribute("customer");
            request.getRequestDispatcher("index.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ShowRestaraunts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

// public class ShowCIMServlet extends HttpServlet {

//     @Override

//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//         HttpSession session = request.getSession();
//         //ValidatorCIM validator = new ValidatorCIM();

//         //String email = request.getParameter("email");
//         CIMManager manager = (CIMManager) session.getAttribute("manager");

//         try {
//             ArrayList<CIM> customers = manager.fectCIM();
//             session.setAttribute("customers", customers);
//             request.getRequestDispatcher("showCIM.jsp").include(request, response);
//         } catch (SQLException ex) {
//             Logger.getLogger(ShowCIMServlet.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         //session.setAttribute("customer", customer);
        
//     }

// }
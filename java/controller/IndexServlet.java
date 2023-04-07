package controller;

import controller.rms.GetAllCategoryServlet;
import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// Servlet to retrieve restaurants and display on index.jsp
@WebServlet(name = "controller/IndexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

        try {
            ArrayList<Restaurant> restaurants = manager.seeAllRestaurants();
            session.setAttribute("restaurants", restaurants);
            if(session.getAttribute("allRestos") == null) {
                session.setAttribute("allRestos", restaurants);
            }
            request.getRequestDispatcher("index.jsp").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

}

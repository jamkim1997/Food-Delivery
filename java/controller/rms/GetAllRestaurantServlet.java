package controller.rms;

import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;
import model.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/GetAllRestaurantServlet", value = "/all-restaurant")
public class GetAllRestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session);

        // set restaurants for session only, manageRes will use this attribute to retrieve all active res
        try {
            ArrayList<Restaurant> restaurants = manager.seeAllRestaurants();
            session.setAttribute("restaurants", restaurants);
            request.getRequestDispatcher("manageRes.jsp").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(GetAllRestaurantServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("manageRes.jsp").include(request, response);
        }

    }

    private void clear(HttpSession session) {
        session.setAttribute("rStrNameError", "");
        session.setAttribute("rModifySuccess", "");
        session.setAttribute("rPostCodeError", "");
        session.setAttribute("rStateError", "");
        session.setAttribute("rSuburbError", "");
        session.setAttribute("rCountryError", "");
        session.setAttribute("rABNError", "");
        session.setAttribute("rAcctNumError", "");
        session.setAttribute("rAcctNameError", "");
        session.setAttribute("rBSBError", "");
        session.setAttribute("rStrNumError", "");
        session.setAttribute("rResNameError", "");
        session.setAttribute("restaurant", null);
    }

}



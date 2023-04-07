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

@WebServlet(name = "controller/rms/FindRestaurantServlet", value = "/find-res")
public class FindRestaurantServlet extends HttpServlet {

    // For searchbar in index/main menu or in Manage Restaurant
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

        String resName = request.getParameter("name");
        String view = request.getParameter("view");

        session.setAttribute("input", resName);

        ArrayList<Restaurant> restaurants;

        try {
            restaurants = manager.findRestaurant(resName);
            session.setAttribute("restaurants", restaurants);
            if (view.equals("menu")) { request.getRequestDispatcher("index.jsp").include(request, response); }
            else { request.getRequestDispatcher("manageRes.jsp").include(request, response); }
        } catch (Exception e) {
            Logger.getLogger(FindRestaurantServlet.class.getName()).log(Level.SEVERE, null, e);
            if (view.equals("menu")) { request.getRequestDispatcher("index").include(request, response); }
            else { request.getRequestDispatcher("all-restaurant").include(request, response); }
        }

    }

    // for retrieval using href
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        String resID = request.getParameter("id");

        // For Edit Category, since we are guaranteed to only get one category
        Restaurant restaurant;

        try {
            restaurant = manager.findRestaurant(Integer.parseInt(resID));
            session.setAttribute("restaurant", restaurant);
            request.getRequestDispatcher("modifyRes.jsp?edit=true").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(FindRestaurantServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("all-restaurant").include(request, response);
        }
    }

}

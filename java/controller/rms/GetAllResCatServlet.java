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

@WebServlet(name = "GetAllResCatServlet", value = "/all-rescat")
public class GetAllResCatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession();
            ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

            String ref = request.getParameter("ref");

            try {
                ArrayList<Restaurant> restaurants = manager.seeAllRestaurants();
                ArrayList<RCategory> rcategories = manager.seeAllRCategories();
                session.setAttribute("restaurants", restaurants);
                session.setAttribute("rcategories", rcategories);
                request.getRequestDispatcher("setResCat.jsp?ref=" + ref).include(request, response);
            } catch (Exception e) {
                Logger.getLogger(GetAllRestaurantServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("setResCat.jsp?ref=" + ref).include(request, response);
            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}

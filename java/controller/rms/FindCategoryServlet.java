package controller.rms;

import controller.Validator;
import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/FindCategoryServlet", value = "/find-cat")
public class FindCategoryServlet extends HttpServlet {

    // Post for form searches
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

        String catName = request.getParameter("name");

        // For searchbar to search category
        ArrayList<RCategory> rcategories;

        try {
            rcategories = manager.findRCategory(catName);
            session.setAttribute("rcategories", rcategories);
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(GetAllCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        }
    }

    // for retrieval using href
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        String catID = request.getParameter("id");

        // For Edit Category, since we are guaranteed to only get one category
        RCategory rcategory;

        try {
            rcategory = manager.findRCategory(Integer.parseInt(catID));
            session.setAttribute("rcategory", rcategory);
            request.getRequestDispatcher("modifyCat.jsp?edit=true").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(GetAllCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("all-category").include(request, response);
        }

    }


}

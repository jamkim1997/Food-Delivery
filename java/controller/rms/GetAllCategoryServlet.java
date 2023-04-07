package controller.rms;

import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/GetAllCategoryServlet", value = "/all-category")
public class GetAllCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session);

        // set rcategories for session only, manageCat will use this attribute to retrieve all active rcats
        try {
            ArrayList<RCategory> rcategories = manager.seeAllRCategories();
            session.setAttribute("rcategories", rcategories);
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(GetAllCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        }

    }

    private void clear(HttpSession session) {
        session.setAttribute("catNameError", "");
        session.setAttribute("cModifySuccess", "");
        session.setAttribute("catDescError", "");
        session.setAttribute("rcategory", null);
    }

}

package controller.rms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.*;
import model.*;

@WebServlet(name = "controller/rms/ModifyCategoryServlet", value = "/modify-category-details")
public class ModifyCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session); // Used to clear error messages.

        String catName = request.getParameter("name");
        String catDesc = request.getParameter("description");
        String catID = request.getParameter("id");
        boolean hasError = false;

        if (!validator.validateName(catName)) {
            session.setAttribute("catNameError", "Incorrect Name Format - Name must start with a capital letter");
            hasError = true;
        }
        if (!validator.validateDesc(catDesc)) {
            session.setAttribute("catDescError", "1-100 Characters only! No special characters!");
            hasError = true;
        }

        if (hasError) {
            if (!catID.equals("")) { request.getRequestDispatcher("modifyCat.jsp?edit=true").include(request, response); }
            else { request.getRequestDispatcher("modifyCat.jsp").include(request, response); }
            return;
        }

        // If ID is empty means we want to create a new record
        // ID is a hidden field which will have an ID if we are modifying an existing record
        if (catID.equals("")) {
            try {
                manager.createCategory(new RCategory(catName, catDesc));
                session.setAttribute("cModifySuccess", "Successfully Added Category!");
                request.getRequestDispatcher("modifyCat.jsp").include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("modifyCat.jsp").include(request, response);
            }
        }
        else {
            try {
                RCategory rcategory = manager.updateCategory(new RCategory(Integer.parseInt(catID), catName, catDesc));
                session.setAttribute("rcategory", rcategory);
                session.setAttribute("cModifySuccess", "Successfully Edited Category!");
                request.getRequestDispatcher("modifyCat.jsp?edit=true").include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("all-category").include(request, response);
            }
        }
    }

    private void clear(HttpSession session) {
        session.setAttribute("catNameError", "");
        session.setAttribute("cModifySuccess", "");
        session.setAttribute("catDescError", "");
    }

}

package controller.rms;

import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/DeleteCatServlet", value = "/delete-cat")
public class DeleteCatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session);

        int cat = Integer.parseInt(request.getParameter("cat"));

        try {
            manager.deleteCategory(cat);
            session.setAttribute("cDeleteSuccess", "Successfully Deleted Category!");
            request.getRequestDispatcher("all-category").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(DeleteCatServlet.class.getName()).log(Level.SEVERE, null, e);
            session.setAttribute("cDeleteError", "Unable To Delete Category!");
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        }
    }

    private void clear(HttpSession session) {
        session.setAttribute("cDeleteError", "");
        session.setAttribute("cDeleteSuccess", "");
    }

}

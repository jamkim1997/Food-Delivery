package controller.rms;

import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/DeleteRestaurantServlet", value = "/delete-res")
public class DeleteRestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session);

        int resID = Integer.parseInt(request.getParameter("res"));

        try {
            manager.deleteRestaurant(resID);
            session.setAttribute("rDeleteSuccess", "Successfully Deleted Restaurant!");
            request.getRequestDispatcher("all-restaurant").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(DeleteCatServlet.class.getName()).log(Level.SEVERE, null, e);
            session.setAttribute("rDeleteError", "Unable To Delete Restaurant!");
            request.getRequestDispatcher("manageRes.jsp").include(request, response);
        }
    }
    private void clear(HttpSession session) {
        session.setAttribute("rDeleteError", "");
        session.setAttribute("rDeleteSuccess", "");
    }

}

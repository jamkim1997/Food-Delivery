package controller.rms;

import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/RestaurantActivationServlet", value = "/activate-res")
public class RestaurantActivationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

        int resID = Integer.parseInt(request.getParameter("res"));
        Logger.getLogger(DeleteCatServlet.class.getName()).log(Level.SEVERE, resID + "");

        try {
            int result = manager.restaurantActivation(resID);
            if (result == 1) session.setAttribute("activateSuccess", "Successfully Activated Restaurant!");
            else if (result == 0) session.setAttribute("activateSuccess", "Successfully Deactivated Restaurant!");
            request.getRequestDispatcher("index").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(RestaurantActivationServlet.class.getName()).log(Level.SEVERE, null, e);
            session.setAttribute("activationError", "Unable To De/Activate Restaurant!");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }

    }
}

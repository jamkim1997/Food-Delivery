package controller.rms;

// import controller.Validator; // Validator implementation in the future
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 import dao.ResDBManager;
 import jakarta.servlet.ServletException;
 import jakarta.servlet.annotation.*;
 import jakarta.servlet.http.HttpServlet;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import jakarta.servlet.http.HttpSession;
 import model.*;

 // Update what
 @WebServlet(name = "controller/rms/UpdateCategoryServlet", value = "/update-rescat")
 public class SetResCatServlet extends HttpServlet {

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {

            HttpSession session = request.getSession();
            ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

            String resID = request.getParameter("res");
            String catID = request.getParameter("cat");
            String ref = request.getParameter("ref");

            try {
                manager.setRestaurantCategory(Integer.parseInt(resID), Integer.parseInt(catID));
                request.getRequestDispatcher("all-rescat?ref=" + ref).include(request, response);
            } catch (Exception e) {
                Logger.getLogger(SetResCatServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("all-rescat?ref=" + ref).include(request, response);
            }

      }

 }
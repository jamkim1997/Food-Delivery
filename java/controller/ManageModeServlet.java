package controller;

// import controller.Validator; // Validator implementation in the future
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.*;
import model.*;

@WebServlet(name = "controller/ManageModeServlet", value = "/manage-mode")
public class ManageModeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if ((boolean) session.getAttribute("manageMode")) {
            clear(session);
        }
        else {
            session.setAttribute("manageMode", true);
        }

        request.getRequestDispatcher("/index.jsp").include(request, response);

    }

    private void clear(HttpSession session) {
        session.setAttribute("manageMode", false);
    }

}

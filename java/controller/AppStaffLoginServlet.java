package controller;

import com.mysql.cj.Session;
import controller.rms.ModifyCategoryServlet;
import dao.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.AppStaff;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/AppStaffServlet", value = "/appstaff-login")
public class AppStaffLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager) session.getAttribute("DBManager");
        clear(session);

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        boolean hasError = false;

        if (!validator.validateEmail(email)) {
            session.setAttribute("emailError", "Incorrect Email Format");
            hasError = true;
        }
        if (!validator.validatePassword(pass)) {
            session.setAttribute("passwordError", "At least 1 Uppercase, 1 Number, 1 Special Character and 8 characters long");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("appStaffLogin.jsp").include(request, response);
            return;
        }

        try {
            AppStaff appStaff = manager.appStaffLogin(email, pass);
            if (appStaff == null) { session.setAttribute("credentialsError", "Incorrect Credentials"); }
            else { session.setAttribute("appStaff", appStaff); }
            request.getRequestDispatcher("index").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(AppStaffLoginServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("appStaffLogin.jsp").include(request, response);
        }

    }

    private void clear(HttpSession session) {
        session.setAttribute("passwordError", "");
        session.setAttribute("emailError", "");
        session.setAttribute("credentialsError", "");
        session.setAttribute("appStaff", null);
    }
}

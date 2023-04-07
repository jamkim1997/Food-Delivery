package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DBConnector;
import dao.DBManager;
import model.Customer;

@WebServlet(name = "logoutServlet", value = "/logoutServlet")

public class logoutServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").include(request, response);
    }

}
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import java.util.logging.Logger;

import dao.DBConnector;
import dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Restaurant;

@WebServlet(name = "controller/MenuUpdateServlet", value = "/menu-update")
public class MenuUpdateServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        if(manager == null) {
            createManager(request, response);
        }
        manager = (DBManager) session.getAttribute("manager");

           
            ArrayList<Restaurant> restaraunts = (ArrayList<Restaurant>) session.getAttribute("allRestos");
            int id = Integer.parseInt(request.getParameter("id"));

            for (Restaurant restaurant : restaraunts) {
                if(restaurant.getRestaurantID() == id) {
                    session.setAttribute("currentResto", restaurant);
                    request.getRequestDispatcher("/menuStaffPage.jsp").include(request, response);
                    return;
                }
            }
    }
    
    private void createManager(HttpServletRequest request, HttpServletResponse response) {
        DBConnector db;
        DBManager manager;
        Connection conn;

        try {
            db = new DBConnector();
            HttpSession session = request.getSession();

            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}
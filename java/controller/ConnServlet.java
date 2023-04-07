package controller;

import java.io.IOException;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.logging.Level;

import java.util.logging.Logger;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import dao.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ConnServlet", value = "/ConnServlet")
public class ConnServlet extends HttpServlet {

    private DBConnector db;

    private DBManager manager;
    private ResDBManager resmanager;

    private Connection conn;

    private Connection conn2;


    @Override //Create and instance of DBConnector for the deployment session

    public void init() {

        try {

            db = new DBConnector();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }



    @Override //Add the DBConnector, DBManager, Connection instances to the session

    protected void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        conn = db.openConnection();
        conn2 = db.openConnection();

        try {

            manager = new DBManager(conn);
            resmanager = new ResDBManager(conn, conn2);
            // ResDBManager will need to use 2 connections for concurrent querys

        } catch (SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        session.setAttribute("DBManager", manager);
        session.setAttribute("ResDBManager", resmanager);
        request.getRequestDispatcher("index").include(request, response);

    }



    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)

    public void destroy() {

        try {

            db.closeConnection();

        } catch (SQLException ex) {

            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}

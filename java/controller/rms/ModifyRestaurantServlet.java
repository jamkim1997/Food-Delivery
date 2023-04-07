package controller.rms;

import controller.Validator;
import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;
import model.Restaurant;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/ModifyRestaurantServlet", value = "/modify-restaurant-details")
public class ModifyRestaurantServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session); // Used to clear error messages.

        String resID = request.getParameter("id");
        String image = request.getParameter("image");
        String resName = request.getParameter("resName");
        String strNum = request.getParameter("strNum");
        String strName = request.getParameter("strName");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        String country = request.getParameter("country");
        // Won't validate activation because jsp will only have a on/off button to collect 1 or 0 only
        String activation = request.getParameter("activate");
        // Temporary set activation to permanent
        activation = "1";
        String abn = request.getParameter("abn");
        String acctName = request.getParameter("acctName");
        String bsb = request.getParameter("bsb");
        String acctNum = request.getParameter("acctNum");
        boolean hasError = false;

        if (!validator.validateName(strName)) {
            session.setAttribute("rStrNameError", "Street Name must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateResName(resName)) {
            session.setAttribute("rResNameError", "Restaurant Name must be 1 - 30 characters long! No escape characters!");
            hasError = true;
        }

        if (!validator.validatePostCode(postcode)) {
            session.setAttribute("rPostCodeError", "Postcode must be 4 digits long");
            hasError = true;
        }

        if (!validator.validateName(state)) {
            session.setAttribute("rStateError", "State must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateName(suburb)) {
            session.setAttribute("rSuburbError", "Suburb must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateName(country)) {
            session.setAttribute("rCountryError", "Country must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateABN(abn)) {
            session.setAttribute("rABNError", "ABN must be 11 digits long");
            hasError = true;
        }

        if (!validator.validateAcctNum(acctNum)) {
            session.setAttribute("rAcctNumError", "Account Number must be 6 digits long");
            hasError = true;
        }

        if (!validator.validateName(acctName)) {
            session.setAttribute("rAcctNameError", "Account Name must start with a capital letter");
            hasError = true;
        }

        if (!validator.validateBSB(bsb)) {
            session.setAttribute("rBSBError", "BSB must be 6 digits long");
            hasError = true;
        }

        if (!validator.validateStrNum(strNum)) {
            session.setAttribute("rStrNumError", "Street Number must be digits only or empty");
            hasError = true;
        }

        if (hasError) {
            if (!resID.equals("")) { request.getRequestDispatcher("modifyRes.jsp?edit=true").include(request, response); }
            else { request.getRequestDispatcher("modifyRes.jsp").include(request, response); }
            return;
        }

        // If ID is empty means we want to create a new record
        // ID is a hidden field which will have an ID if we are modifying an existing record
        if (resID.equals("")) {
            try {
                manager.createRestaurant(new Restaurant(image, resName, Integer.parseInt(strNum), strName,
                        Integer.parseInt(postcode), state, suburb, country,
                        (Integer.parseInt(activation) == 1), Long.parseLong(abn),
                        acctName, Integer.parseInt(bsb), Integer.parseInt(acctNum)));
                session.setAttribute("rModifySuccess", "Successfully Added Restaurant!");
                request.getRequestDispatcher("modifyRes.jsp").include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyRestaurantServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("modifyRes.jsp").include(request, response);
            }
        }
        else {
            try {
                Restaurant restaurant = manager.updateRestaurant(new Restaurant(Integer.parseInt(resID), image,
                        resName, Integer.parseInt(strNum), strName,
                        Integer.parseInt(postcode), state, suburb, country,
                        (Integer.parseInt(activation) == 1), Long.parseLong(abn),
                        acctName, Integer.parseInt(bsb), Integer.parseInt(acctNum)));
                session.setAttribute("rModifySuccess", "Successfully Edited Restaurant!");
                session.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("modifyRes.jsp?edit=true").include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyRestaurantServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("all-restaurant").include(request, response);
            }
        }

    }

    private void clear(HttpSession session) {
        session.setAttribute("rStrNameError", "");
        session.setAttribute("rModifySuccess", "");
        session.setAttribute("rPostCodeError", "");
        session.setAttribute("rStateError", "");
        session.setAttribute("rSuburbError", "");
        session.setAttribute("rCountryError", "");
        session.setAttribute("rABNError", "");
        session.setAttribute("rAcctNumError", "");
        session.setAttribute("rAcctNameError", "");
        session.setAttribute("rBSBError", "");
        session.setAttribute("rStrNumError", "");
        session.setAttribute("rResNameError", "");
    }
}

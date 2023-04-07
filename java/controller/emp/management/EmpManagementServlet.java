package controller.emp.management;
import exceptions.InvalidPrivilegeNumException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import service.RestaurantEmpService;
import service.impl.RestaurantEmpServiceImpl;

import java.io.IOException;
import java.util.*;

/**
 * Handling employee-related business servlet
 * @author Hao Zeng
 * @version 1.0
 */
@WebServlet("/empManage/*")
public class EmpManagementServlet extends HttpServlet{

    private RestaurantEmpService restaurantEmpService = new RestaurantEmpServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // Get the request path first, and then remove the root path of the project will get the servlet path correctly.
        String servletPath = request.getRequestURI().substring(request.getContextPath().length());
        // Use template method pattern to reduce the number of classes
        if (servletPath.equals("/empManage/removeEmp"))
            doRemove(request, response);
        else if (servletPath.equals("/empManage/edit"))
            doEdit(request, response);
        else if (servletPath.equals("/empManage/showEmp"))
            doList(request, response);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //For demo, as there is currently no combined login system---------------------------------------------------------------
        session.setAttribute("staff", restaurantEmpService.getStaff("1"));
        //-------------------------------------------------------------------------------
        Staff staff = (Staff) session.getAttribute("staff");
        List<Staff> staffs = restaurantEmpService.empList(staff.getRestaurantID(), 0, 9);
        request.setAttribute("StoreEmps", staffs);
        Object success = request.getAttribute("success");
        Object fail = request.getAttribute("fail");
        if (success != null)
            request.setAttribute("success", success);
        else if (fail != null)
            request.setAttribute("fail", true);
        request.getRequestDispatcher("/empManagement.jsp").forward(request, response);
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String staffId = request.getParameter("staffId");
        String privilege = request.getParameter("privilege");
        String position = request.getParameter("position");
        try{
            restaurantEmpService.updatePrivilegeAndPosition(staffId, privilege, position);
            request.setAttribute("success", "success");
        } catch (InvalidPrivilegeNumException e) {
            request.setAttribute("fail", true);
        } finally {
            request.getRequestDispatcher("/empManage/showEmp").forward(request, response);
        }

    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        restaurantEmpService.removeEmp(request.getParameter("id"));
        request.setAttribute("success", "success");
        request.getRequestDispatcher("/empManage/showEmp").forward(request, response);
    }
}

package controller.coupon;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Restaurant;
import service.CouponService;
import service.impl.CouponServiceImpl;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Handling coupon-related business servlet
 * @author Hao Zeng
 * @version 1.0
 */
@WebServlet("/coupon/*")
public class CouponServlet extends HttpServlet {

    private CouponService couponService = new CouponServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getRequestURI().substring(request.getContextPath().length());
        if (servletPath.equals("/coupon/create"))
            doCreate(request, response);
        else if (servletPath.equals("/coupon/findRes"))
            doFindRes(request, response);
        else if (servletPath.equals("/coupon/findItems"))
            doFindItems(request, response);
        else if (servletPath.equals("/coupon/showCoupons"))
            doShowCoupons(request, response);
        else if (servletPath.equals("/coupon/deleteCoupon"))
            doDeleteCoupon(request, response);
        else if (servletPath.equals("/coupon/update"))
            doUpdate(request, response);
        else if (servletPath.equals("/coupon/findARes"))
            doFindARes(request, response);
    }

    private void doFindARes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String couponId = request.getParameter("cId");
        response.getWriter().print(couponService.getARes(couponId));
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String couponId = request.getParameter("cId");
        String couponName = request.getParameter("cName");
        String couponScope = request.getParameter("cScope");
        String couponResId = request.getParameter("resId");
        String couponItemId = request.getParameter("itemId");
        String couponMinMoney = request.getParameter("minMoney");
        String couponValue = request.getParameter("value");
        String couponDescription = request.getParameter("description");
        // Image upload function does not implement, hard code here
        String couponImage = "images/no-photo-available.jpeg";
        if (!couponService.updateCoupon(couponId, couponName, couponScope, couponResId, couponItemId, couponMinMoney, couponValue, couponDescription,  couponImage))
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    private void doDeleteCoupon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String ids = request.getParameter("couponIds");
        if (!couponService.deleteCoupons(ids))
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    private void doShowCoupons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().print(new Gson().toJson(couponService.getCoupons()));
    }

    private void doFindItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String resId = request.getParameter("resId");
        if (!resId.isEmpty())
            response.getWriter().print(new Gson().toJson(couponService.itemInfo(Integer.parseInt(resId))));
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String couponName = request.getParameter("cName");
        String couponScope = request.getParameter("cScope");
        String couponResId = request.getParameter("resId");
        String couponItemId = request.getParameter("itemId");
        String couponMinMoney = request.getParameter("minMoney");
        String couponValue = request.getParameter("value");
        String couponDescription = request.getParameter("description");
        // Image upload function does not implement, hard code here
        String couponImage = "images/no-photo-available.jpeg";
        if (!couponService.addCoupon(couponName, couponScope, couponResId, couponItemId, couponMinMoney, couponValue, couponDescription,  couponImage))
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    private void doFindRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print(new Gson().toJson(couponService.resInfo()));
    }
}

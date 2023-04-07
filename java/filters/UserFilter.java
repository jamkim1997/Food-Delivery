package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Filtering for user who is not an appStaff
 * @author Hao Zeng
 * @version 1.0
 */
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("appStaff") != null){
            chain.doFilter(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}

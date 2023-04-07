package filters;

import jakarta.servlet.*;
import org.apache.ibatis.session.SqlSession;
import utils.SqlSessionUtil;

import java.io.IOException;

/**
 * Filter when the servlet is loaded. Create a SqlSession object and close it at the end
 * @author Hao Zeng
 * @version 1.0
 */
public class SqlSessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SqlSession session = SqlSessionUtil.openSqlSession();
        try{
             chain.doFilter(request,response);
             session.commit();
        }catch (Exception e){
            session.rollback();
            e.printStackTrace();
        }finally {
            SqlSessionUtil.close(session);
        }
    }
}

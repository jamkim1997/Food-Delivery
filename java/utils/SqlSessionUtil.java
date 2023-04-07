package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSessionUtil for mybatis
 * @author Hao Zeng
 * @version 1.0
 */
public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;
    /*
       Use threadlocal solves transaction problems
    */
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    //No objects need to be instantiated, as the methods in util class are all static
    private SqlSessionUtil(){}

    /*
        A sqlSessionFactory object corresponds to one environment, i.e. one database
        Since we only have one database, the object only needs to be created once.
        Parsing the mybatis-config.xml file and create the SqlSessionFactory object.
     */
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null)
        {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void close(SqlSession sqlSession) {
        if (sqlSession != null)
        {
            sqlSession.close();
            threadLocal.remove();
        }
    }

}

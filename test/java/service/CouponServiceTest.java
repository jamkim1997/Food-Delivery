package service;

import model.MenuItem;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import service.impl.CouponServiceImpl;
import utils.SqlSessionUtil;

import java.util.List;

public class CouponServiceTest {
    //The following tests require a database connection
    @Test
    public void testInsert()
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        CouponService couponServiceImpl = new CouponServiceImpl();
        couponServiceImpl.addCoupon("as", "Specific items in specific store", "1", "1,2,3", "23.23", "20.1", "sad", "images/no-photo-available.jpeg");
        SqlSessionUtil.close(sqlSession);
    }

}

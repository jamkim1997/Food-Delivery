package service;

import exceptions.InvalidPrivilegeNumException;
import model.Staff;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import service.impl.RestaurantEmpServiceImpl;
import utils.SqlSessionUtil;
import utils.Validator;

import java.util.List;
public class RestaurantEmpServiceTest{
    @Test
    public void testInvalidPrivilegeNumException()
    {
        RestaurantEmpService rempImpl = new RestaurantEmpServiceImpl();
        String invalidPrivilege = "-2";
        Assertions.assertThrows(InvalidPrivilegeNumException.class, ()->{
            rempImpl.updatePrivilegeAndPosition("2", invalidPrivilege, "a");
        });
    }

//The following tests require a database connection
/*
    @Test
    public void testEmpList()
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        RestaurantEmpService rempImpl = new RestaurantEmpServiceImpl();
        List<Staff> staffs = rempImpl.empList(1, 0, 9);
        System.out.println(staffs);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectUserById()
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        Object o = sqlSession.selectOne("mapper.UserMapper.selectById");
        System.out.println((User) o);
        SqlSessionUtil.close(sqlSession);
    }
    @Test
    public void testSelectStaffById()
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        Object o = sqlSession.selectOne("mapper.StaffMapper.selectById", 2);
        System.out.println((Staff) o);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testUpdatePrivilegeAndPosition() throws Exception
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        RestaurantEmpService rempImpl = new RestaurantEmpServiceImpl();
        String staffId = "3";
        rempImpl.updatePrivilegeAndPosition(staffId, "3", "AS");
        Staff staff = rempImpl.getStaff(staffId);
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testRemoveEmp()
    {
        SqlSession sqlSession = SqlSessionUtil.openSqlSession();
        RestaurantEmpService rempImpl = new RestaurantEmpServiceImpl();
        String staffId = "2";
        rempImpl.removeEmp(staffId);
        Staff staff = rempImpl.getStaff(staffId);
        Assertions.assertEquals(-1, staff.getPrivilege());
    }
*/
}

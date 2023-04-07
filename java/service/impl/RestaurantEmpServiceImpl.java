package service.impl;

import exceptions.InvalidPrivilegeNumException;
import mapper.StaffMapper;
import model.Staff;
import org.apache.ibatis.session.SqlSession;
import service.RestaurantEmpService;
import utils.SqlSessionUtil;
import utils.Validator;

import java.util.List;

/**
 * RestaurantEmpService implementation class
 * @author Hao Zeng
 * @version 1.0
 */
public class RestaurantEmpServiceImpl implements RestaurantEmpService {

    @Override
    public List<Staff> empList(int restaurantId, int privilege1, int privilege2) {
        return SqlSessionUtil.openSqlSession().getMapper(StaffMapper.class).selectStaffByRestaurantIDAndPrivilegeRange(restaurantId, privilege1, privilege2);
    }

    @Override
    public void updatePrivilegeAndPosition(String staffId, String privilege, String position) throws InvalidPrivilegeNumException {
            if (!Validator.validatePrivilege(privilege))
                throw new InvalidPrivilegeNumException("Invalid privilege");
            else
            {
                Integer intPrivilege = Integer.parseInt(privilege);
                //The store owner's privilege is 10, so only levels below 10 can be set.
                if (intPrivilege >= 10)
                    throw new InvalidPrivilegeNumException("Invalid privilege");
                SqlSessionUtil.openSqlSession().getMapper(StaffMapper.class).updateById(Integer.parseInt(staffId), null, null, intPrivilege, position);
            }

    }

    @Override
    public void removeEmp(String staffId) {
        SqlSessionUtil.openSqlSession().getMapper(StaffMapper.class).updateById(Integer.parseInt(staffId), null, null, -1, "Departure");
    }

    @Override
    public Staff getStaff(String staffId) {
        return SqlSessionUtil.openSqlSession().getMapper(StaffMapper.class).selectById(Integer.parseInt(staffId));
    }

}

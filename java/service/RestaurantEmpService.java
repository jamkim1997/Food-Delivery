package service;

import exceptions.InvalidPrivilegeNumException;
import model.*;

import java.util.List;

/**
 * Restaurant staff Service
 * @author Hao Zeng
 * @version 1.0
 */
public interface RestaurantEmpService {
    List<Staff> empList(int restaurantId, int privilege1, int privilege2);
    void updatePrivilegeAndPosition(String staffId, String privilege, String position) throws InvalidPrivilegeNumException;
    void removeEmp(String staffId);

    Staff getStaff(String staffId);

}

package mapper;

import model.Staff;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Staff Mapper
 * @author Hao Zeng
 * @version 1.0
 */

public interface StaffMapper {

    int updateById(@Param("staffId")Integer staffId, @Param("userId")Integer userId, @Param("restaurantId")Integer restaurantId, @Param("privilege")Integer privilege, @Param("position")String position);

    Staff selectById(int staffID);

    List<Staff> selectStaffByRestaurantIDAndPrivilegeRange(@Param("restaurantId")int restaurantId, @Param("privilege1")int privilege1, @Param("privilege2")int privilege2);
}

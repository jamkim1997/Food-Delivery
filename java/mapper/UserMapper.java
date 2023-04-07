package mapper;

import model.User;

/**
 * User Mapper
 * @author Hao Zeng
 * @version 1.0
 */
public interface UserMapper {
    User selectById(int userId);
}

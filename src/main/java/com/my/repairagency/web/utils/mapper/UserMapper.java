package com.my.repairagency.web.utils.mapper;

import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.Role;
import com.my.repairagency.repository.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * extracts user entity from resultSet
 */
public class UserMapper implements EntityMapper<User> {
    private static final Logger logger = LogManager.getLogger(UserMapper.class);

    private static final String ID = "user_id";
    private static final String ROLE_ID = "user_role_id";
    private static final String NAME = "user_name";
    private static final String SURNAME = "user_surname";
    private static final String EMAIL = "user_email";
    private static final String PASSWORD = "user_password";
    private static final String ACCOUNT = "ACCOUNT";
    private static final String TITLE = "user_role_title";
    private static UserMapper instance;

    private UserMapper() {
    }

    public static synchronized UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }


    @Override
    public User map(ResultSet rs) throws SQLException {
        logger.trace("user mapping started");
        User user = new User();
        user.setId(-1);

        user.setId(rs.getInt(ID));
        user.setName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setEmail(rs.getString(EMAIL));
        user.setAccount(rs.getBigDecimal(ACCOUNT));
        user.setRoleId(rs.getInt(ROLE_ID));

        logger.debug("mapped user: {}", user);
        return user;
    }

    public UserWithRoleDTO mapUserWithRole(ResultSet rs) throws SQLException {
        logger.trace("user with role mapping started");
        UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
        userWithRoleDTO.setId(-1);
        userWithRoleDTO.setId(rs.getInt(ID));
        userWithRoleDTO.setName(rs.getString(NAME));
        userWithRoleDTO.setSurname(rs.getString(SURNAME));
        userWithRoleDTO.setEmail(rs.getString(EMAIL));
        userWithRoleDTO.setPassword(rs.getString(PASSWORD));
        userWithRoleDTO.setAccount(rs.getBigDecimal(ACCOUNT));
        userWithRoleDTO.setRole(Role.valueOf(rs.getString(TITLE)));

        logger.debug("mapped user with role: {}", userWithRoleDTO);
        return userWithRoleDTO;
    }

    public List<UserWithRoleDTO> mapUsers(ResultSet resultSet) throws SQLException {
        List<UserWithRoleDTO> result = new ArrayList<>();

        while (resultSet.next()){
            result.add(this.mapUserWithRole(resultSet));
        }

        return result;
    }
}
package com.my.repairagency.web.utils.mapper;

import com.my.repairagency.repository.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements EntityMapper<Role> {
    private static final Logger logger = LogManager.getLogger(RoleMapper.class);

    private static final String ID = "user_role_id";
    private static final String title = "user_role_title";
    private static RoleMapper instance;

    private RoleMapper() {
    }

    public static synchronized RoleMapper getInstance() {
        if (instance == null) {
            instance = new RoleMapper();
        }
        return instance;
    }

    @Override
    public Role map(ResultSet rs) throws SQLException {
        logger.trace("Role mapping started");
        Role role = new Role();
        role.setId(-1);
        if (rs.next()) {
            role.setId(rs.getInt(ID));
            role.setTitle(rs.getString(title));
        }
        logger.debug("mapped role: {}", role);
        return role;
    }
}

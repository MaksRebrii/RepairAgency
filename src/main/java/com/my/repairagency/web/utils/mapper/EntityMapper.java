package com.my.repairagency.web.utils.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Defines contract for mapping sql request result set rows to entities.
 * Implementations are not supposed to move cursor of the resultSet via next() method,
 * but only extract information from the row in current cursor position.
 *
 * @param <T> is an entity class
 */
public interface EntityMapper<T> {
    T map(ResultSet rs) throws SQLException;
}

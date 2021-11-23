package com.my.repairagency.repository;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.User;
import com.my.repairagency.web.utils.HashingPassword;
import com.my.repairagency.web.utils.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static UserDAO instance;

    /////////////////////////
    ///     singleton    ////
    /////////////////////////
    private UserDAO() {
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null)
            instance = new UserDAO();
        return instance;
    }


    public boolean isUserExist(String login) {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQuery.UserRequest.GET_USER_ID_BY_EMAIL)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserWithRoleDTO getUserByLogin(String login) throws DAOException {
        UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
        userWithRoleDTO.setId(-1);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQuery.UserRequest.SELECT_USER_WITH_ROLE_BY_LOGIN)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                userWithRoleDTO = UserMapper.getInstance().mapUserWithRole(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error with connecting to database");
            throw new DAOException("Cannot connect to database. Please try later");
        }
        return userWithRoleDTO;
    }

    public UserWithRoleDTO getUserById(int id) {
        UserWithRoleDTO userWithRoleDTO = new UserWithRoleDTO();
        userWithRoleDTO.setId(-1);


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQuery.UserRequest.GET_USER_WITH_ROLE_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                userWithRoleDTO = UserMapper.getInstance().mapUserWithRole(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userWithRoleDTO;
    }

    public void addUser(User user) throws DAOException, EncryptException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQuery.UserRequest.ADD_NEW_USER)) {

            int k = 1;
            preparedStatement.setInt(k++, user.getRoleId());
            preparedStatement.setString(k++, user.getName());
            preparedStatement.setString(k++, user.getSurname());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k, HashingPassword.getSaltedHash(user.getPassword()));
            int res = preparedStatement.executeUpdate();
            System.out.println(res);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("cannot add user1");
        } catch (NoSuchAlgorithmException | EncryptException e) {
            logger.error(e.getMessage());
            throw new EncryptException("cannot add user");
        }

    }

}

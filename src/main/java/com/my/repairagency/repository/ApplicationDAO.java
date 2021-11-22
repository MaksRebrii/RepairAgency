package com.my.repairagency.repository;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.repository.dto.ApplicationCreateRequestDTO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.Application;
import com.my.repairagency.web.utils.mapper.ApplicationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    private static final Logger logger = LogManager.getLogger(ApplicationDAO.class);
    private static ApplicationDAO instance;

    /////////////////////////
    ///     singleton    ////
    /////////////////////////
    private ApplicationDAO() {
    }

    public static synchronized ApplicationDAO getInstance() {
        if (instance == null)
            instance = new ApplicationDAO();
        return instance;
    }

    public List<ApplicationDTO> getAllApplications() throws DAOException {
        logger.trace("getAllApplication started");
        List<ApplicationDTO> result = new ArrayList<>();


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(SQLQuery.ApplicationRequest.GET_ALL_APPLICATIONS)) {

            while (resultSet.next()) {
                Application application = ApplicationMapper.getInstance().map(resultSet);
                UserWithRoleDTO client = UserDAO.getInstance().getUserById(application.getClientId());
                UserWithRoleDTO master;
                if (application.getMasterId() == 0) {
                    master = new UserWithRoleDTO();
                } else {
                    master = UserDAO.getInstance().getUserById(application.getMasterId());
                }
                if (client.getId() == -1 || master.getId() == -1) {
                    logger.warn("can't get application's client or master App id: {} Expected Client id: {} Master id {} But was:  Client id: {} Master id {}",
                            application.getId(), application.getClientId(), application.getMasterId(), client.getId(), master.getId());
                    throw new DAOException("Cannot get applications");
                }
                ApplicationDTO applicationDTO = new ApplicationDTO(application, client, master);
                result.add(applicationDTO);
            }
        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public void addApplication(ApplicationCreateRequestDTO application) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQLQuery.ApplicationRequest.INSERT_NEW_APPLICATION)) {

            int k = 1;
            statement.setInt(k++, application.getClientId());
            statement.setTimestamp(k++, application.getTimeCreation());
            statement.setString(k, application.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            throw new DAOException("Cannot add new application");
        }
    }
}

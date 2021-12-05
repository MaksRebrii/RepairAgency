package com.my.repairagency.repository;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.repository.dto.ApplicationCreateRequestDTO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.CompletionStatus;
import com.my.repairagency.repository.entity.PaymentStatus;
import com.my.repairagency.repository.entity.Role;
import com.my.repairagency.web.utils.mapper.ApplicationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
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
        logger.trace("getAllApplications started");
        List<ApplicationDTO> result;


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(SQLQuery.ApplicationRequest.GET_ALL_APPLICATIONS)) {

            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public void addApplication(ApplicationCreateRequestDTO application) throws DAOException {
        logger.trace("addApplication started");
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

    public void setMaster(int applicationId, int masterId) throws DAOException {
        logger.trace("setMaster started");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.ApplicationRequest.SET_MASTER)) {
            preparedStatement.setInt(1, masterId);
            preparedStatement.setInt(2, applicationId);
            if (preparedStatement.executeUpdate() != 1) {
                logger.warn("Error during setting master  {} to application {}", masterId, applicationId);
                throw new DAOException("Cannot set master to application");
            }

        } catch (SQLException throwables) {
            logger.warn("Error during setting master  {} to application {}. Caused by {}", masterId, applicationId, throwables.getMessage());
            throw new DAOException("Cannot set master to application");
        }
    }

    public void setPrice(int applicationId, BigDecimal price) throws DAOException {
        logger.trace("setPrice started");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQLQuery.ApplicationRequest.SET_PRICE)) {
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setInt(2, applicationId);

            if (preparedStatement.executeUpdate() != 1) {
                logger.warn("Error during setting price  {} to application {}", price, applicationId);
                throw new DAOException("Cannot set price to application");
            }

        } catch (SQLException throwables) {
            logger.warn("Error during setting price  {} to application {}. Caused by {}", price, applicationId, throwables.getMessage());
            throw new DAOException("Cannot set price to application");
        }
    }

    public void changeCompletionStatus(int applicationId, CompletionStatus completionStatus) throws DAOException {
        logger.trace("changeCompletionStatus started");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.ApplicationRequest.CHANGE_COMPLETION_STATUS)) {

            preparedStatement.setString(1, completionStatus.toString());
            preparedStatement.setInt(2, applicationId);

            if (preparedStatement.executeUpdate() != 1) {
                logger.warn("Error during changing completion status  {} to application {}", completionStatus, applicationId);
                throw new DAOException("Cannot change completion status application");
            }

        } catch (SQLException throwables) {
            logger.warn("Error during changing completion status  {} to application {}. Caused by {}", completionStatus, applicationId, throwables.getMessage());
            throw new DAOException("Cannot change completion status to application");
        }
    }

    public void changePaymentStatus(int applicationId, PaymentStatus paymentStatus) throws DAOException {
        logger.trace("changePaymentStatus started");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.ApplicationRequest.CHANGE_PAYMENT_STATUS)) {

            preparedStatement.setString(1, paymentStatus.toString());
            preparedStatement.setInt(2, applicationId);

            if (preparedStatement.executeUpdate() != 1) {
                logger.warn("Error during changing payment status  {} to application {}", paymentStatus, applicationId);
                throw new DAOException("Cannot change payment status application");
            }

        } catch (SQLException throwables) {
            logger.warn("Error during changing payment status  {} to application {}. Caused by {}", paymentStatus, applicationId, throwables.getMessage());
            throw new DAOException("Cannot change payment status to application");
        }
    }

    public List<ApplicationDTO> getAllUserApplication(UserWithRoleDTO user) throws DAOException {
        logger.trace("getAllUserApplication started");
        String query = null;
        List<ApplicationDTO> result = new ArrayList<>();

        if (Role.MASTER.equals(user.getRole())) {
            query = SQLQuery.ApplicationRequest.GET_ALL_MASTER_APPLICATIONS;
        } else {
            query = SQLQuery.ApplicationRequest.GET_ALL_CUSTOMER_APPLICATIONS;
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public void cancelApplication(int applicationId) throws DAOException {
        logger.trace("cancelApplicationStarted");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery.ApplicationRequest.CANCEL)) {

            preparedStatement.setInt(1, applicationId);

            if (preparedStatement.executeUpdate() != 1) {
                logger.warn("Error during canceling   application {}", applicationId);
                throw new DAOException("Cannot cancel application");
            }


        } catch (SQLException throwables) {
            logger.warn("Error during canceling   application {}", applicationId);
            throw new DAOException("Cannot cancel application");
        }
    }


    public List<ApplicationDTO> getAllApplicationsByCompletionStatuses(String[] statuses) throws DAOException {
        logger.trace("getAllApplicationsByStatuses started");
        List<ApplicationDTO> result = new ArrayList<>();

        StringBuilder query = new StringBuilder(SQLQuery.ApplicationRequest.GET_ALL_WHERE_TEMPLATE);

        query.append("completion_status = '").append(statuses[0]).append("'");

        for (int i = 1; i < statuses.length; i++) {
            query.append(" or ").append(" completion_status = '").append(statuses[i]).append("'");
        }
        logger.debug("request {}", query);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(query.toString())) {

            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public List<ApplicationDTO> getAllApplicationsByPaymentStatuses(String[] statuses) throws DAOException {
        logger.trace("getAllApplicationsByStatuses started");
        List<ApplicationDTO> result;

        StringBuilder query = new StringBuilder(SQLQuery.ApplicationRequest.GET_ALL_WHERE_TEMPLATE);

        query.append("payment_status = '").append(statuses[0]).append("'");

        for (int i = 1; i < statuses.length; i++) {
            query.append(" or ").append(" payment_status = '").append(statuses[i]).append("'");
        }
        logger.debug("request {}", query);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(query.toString())) {

            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public List<ApplicationDTO> getAllApplicationsByPrice(int minValue, int maxValue) throws DAOException {
        List<ApplicationDTO> result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ApplicationRequest.GET_ALL_BY_PRICE_RANGE)) {

            statement.setInt(1, minValue);
            statement.setInt(2, maxValue);

            ResultSet resultSet = statement.executeQuery();

            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public List<ApplicationDTO> getAllApplicationsByDate(String minValue, String maxValue) throws DAOException {
        List<ApplicationDTO> result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ApplicationRequest.GET_ALL_BY_DATE_RANGE)) {

            statement.setDate(1, Date.valueOf(LocalDate.parse(minValue)));
            statement.setDate(2, Date.valueOf(LocalDate.parse(maxValue)));

            ResultSet resultSet = statement.executeQuery();

            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }

    public List<ApplicationDTO> getAllApplicationsByMaster(String mask) throws DAOException {
        List<ApplicationDTO> result;
        String master = "%" + mask + "%";


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.ApplicationRequest.GET_ALL_BY_MASTER_MASK)) {

            statement.setString(1, master);

            ResultSet resultSet = statement.executeQuery();

            result = ApplicationMapper.getInstance().mapApplications(resultSet);

        } catch (SQLException e) {
            logger.warn("error while getting application. Caused by {}", e.getMessage());
            throw new DAOException("Cannot get applications");
        }

        return result;
    }
}

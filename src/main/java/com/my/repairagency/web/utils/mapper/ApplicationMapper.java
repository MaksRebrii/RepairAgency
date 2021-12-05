package com.my.repairagency.web.utils.mapper;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.Application;
import com.my.repairagency.repository.entity.CompletionStatus;
import com.my.repairagency.repository.entity.PaymentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMapper implements EntityMapper<Application> {

    private static final Logger logger = LogManager.getLogger(ApplicationMapper.class);

    private static final String ID = "application_id";
    private static final String CLIENT_ID = "client_id";
    private static final String MASTER_ID = "master_id";
    private static final String DATE = "date";
    private static final String COMPLETION_STATUS = "completion_status";
    private static final String PAYMENT_STATUS = "payment_status";

    private static final String DESCRIPTION = "application_description";
    private static final String PRICE = "application_price";
    private static final String REVIEW = "application_review";
    private static ApplicationMapper instance;

    private ApplicationMapper() {
    }

    public static synchronized ApplicationMapper getInstance() {
        if (instance == null) {
            instance = new ApplicationMapper();
        }
        return instance;
    }

    @Override
    public Application map(ResultSet resultSet) throws SQLException {
        logger.trace("Application mapping started");
        Application application = new Application();
        application.setId(-1);
        application.setId(resultSet.getInt(ID));
        application.setClientId(resultSet.getInt(CLIENT_ID));
        application.setMasterId(resultSet.getInt(MASTER_ID));
        application.setDescription(resultSet.getString(DESCRIPTION));
        application.setPrice(resultSet.getBigDecimal(PRICE));
        application.setReview(resultSet.getString(REVIEW));
        application.setDate(resultSet.getTimestamp(DATE));
        application.setPaymentStatus(PaymentStatus.valueOf(resultSet.getString(PAYMENT_STATUS)));
        application.setCompletionStatus(CompletionStatus.valueOf(resultSet.getString(COMPLETION_STATUS)));

        logger.debug("mapped application: {}", application);
        return application;
    }

    public List<ApplicationDTO> mapApplications(ResultSet resultSet) throws SQLException {
        List<ApplicationDTO> result = new ArrayList<>();
        while (resultSet.next()) {
            Application application = this.map(resultSet);
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
                throw new SQLException();
            }
            ApplicationDTO applicationDTO = new ApplicationDTO(application, client, master);
            result.add(applicationDTO);
        }
        return result;
    }
}

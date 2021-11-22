package com.my.repairagency.web.utils.mapper;

import com.my.repairagency.repository.entity.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper implements EntityMapper<Application> {

    private static final Logger logger = LogManager.getLogger(ApplicationMapper.class);

    private static final String ID = "application_id";
    private static final String CLIENT_ID = "client_id";
    private static final String MASTER_ID = "master_id";
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
    public Application map(ResultSet rs) throws SQLException {
        logger.trace("Application mapping started");
       Application application = new Application();
       application.setId(-1);
       if(rs.next()){
           application.setId(rs.getInt(ID));
           application.setClientId(rs.getInt(CLIENT_ID));
           application.setMasterId(rs.getInt(MASTER_ID));
           application.setDescription(rs.getString(DESCRIPTION));
           application.setPrice(rs.getBigDecimal(PRICE));
           application.setReview(rs.getString(REVIEW));
       }
       logger.debug("mapped application: {}", application);
       return application;
    }
}

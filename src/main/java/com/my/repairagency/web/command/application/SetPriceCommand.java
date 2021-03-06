package com.my.repairagency.web.command.application;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class SetPriceCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SetPriceCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command started");
        final int applicationId = Integer.parseInt(req.getParameter("applicationId"));
        final BigDecimal price = new BigDecimal(req.getParameter("price"));
        ApplicationDAO.getInstance().setPrice(applicationId, price);
        String referer = "error.jsp";
        try {
            referer = new URI(req.getHeader("referer")).getPath();
            referer = referer.concat("?command=getAllApplications");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return referer;
    }
}

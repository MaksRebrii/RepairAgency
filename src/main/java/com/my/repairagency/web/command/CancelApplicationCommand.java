package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.ApplicationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

public class CancelApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CancelApplicationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");

        final int applicationId = Integer.parseInt(req.getParameter("applicationId"));
        ApplicationDAO.getInstance().cancelApplication(applicationId);

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

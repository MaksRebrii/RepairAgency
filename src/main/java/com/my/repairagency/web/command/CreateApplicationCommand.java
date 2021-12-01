package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.dto.ApplicationCreateRequestDTO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

public class CreateApplicationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CreateApplicationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException {
        logger.trace("command started");

        final UserWithRoleDTO client = ((UserWithRoleDTO) req.getSession().getAttribute("user"));
        System.out.println(client);
        final String description = req.getParameter("description");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationCreateRequestDTO application = new ApplicationCreateRequestDTO(client.getId(), description, time);
        logger.debug("received application {}", application);

        ApplicationDAO.getInstance().addApplication(application);

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

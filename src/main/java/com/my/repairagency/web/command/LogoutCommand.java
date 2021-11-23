package com.my.repairagency.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command
 */
public class LogoutCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.trace("Command start");
        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        logger.trace("Command finished");
        return "login.jsp";
    }
}

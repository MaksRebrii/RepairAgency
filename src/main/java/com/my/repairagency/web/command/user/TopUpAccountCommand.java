package com.my.repairagency.web.command.user;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class TopUpAccountCommand implements Command {

    private static final Logger logger = LogManager.getLogger(TopUpAccountCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("command started");
        final int userId = Integer.parseInt(req.getParameter("userId"));
        final BigDecimal sum = new BigDecimal(req.getParameter("sum"));

        logger.debug("updating user's {} account to {}", userId, sum);
        UserDAO.getInstance().updateAccount(userId, sum);

        String referer = "error.jsp";
        try {
            referer = new URI(req.getHeader("referer")).getPath();
            referer = referer.concat("?command=getAllUsers");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return referer;
    }
}

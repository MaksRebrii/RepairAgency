package com.my.repairagency.web.command;


import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.web.utils.HashingPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command started");
        req.getSession().removeAttribute("error");
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        UserWithRoleDTO user = UserDAO.getInstance().getUserByLogin(email);
        if(user.getId() == -1 || !HashingPassword.check(password, user.getPassword())){
            req.getSession().setAttribute("error", "Unknown login or password try again.");
            return "login.jsp";
        }
        user.setPassword(null);
        req.getSession().setAttribute("user", user);
        logger.trace("user {} authorized", user);

        return "main.jsp";

    }
}

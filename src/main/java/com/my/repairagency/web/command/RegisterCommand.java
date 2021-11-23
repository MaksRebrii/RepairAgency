package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        req.getSession().removeAttribute("error");
        logger.trace("command started");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        final int roleId = Integer.parseInt(req.getParameter("role"));
        if (UserDAO.getInstance().isUserExist(email)) {
            req.getSession().setAttribute("error", "User with this email is already registered");
            return "register.jsp";
        }
        User user = new User(roleId, name, surname, email, password);
        logger.debug("user to register: {}", user);

        UserDAO.getInstance().addUser(user);


        return "main.jsp";
    }
}



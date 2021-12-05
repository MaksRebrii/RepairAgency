package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FilterByRoleCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FilterByRoleCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("command start");

        String[] roles = req.getParameterValues("role");

        List<UserWithRoleDTO> userList = UserDAO.getInstance().getAllUsersByRole(roles);
        req.setAttribute("userList", userList);

        return "usersList.jsp";
    }
}

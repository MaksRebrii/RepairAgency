package com.my.repairagency.web.command.user;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUsersCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GetAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");
        List<UserWithRoleDTO> userList = UserDAO.getInstance().getAllUsers();
        req.setAttribute("userList", userList);
        return "usersList.jsp";
    }
}

package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetApplicationsByUserIdCommand implements Command {

    Logger logger = LogManager.getLogger(GetApplicationsByUserIdCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");

        final UserWithRoleDTO user = (UserWithRoleDTO) req.getSession().getAttribute("user");

        List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllUserApplication(user);
        req.setAttribute("applicationList", applicationList);

        return "applicationsListSearching.jsp";
    }
}

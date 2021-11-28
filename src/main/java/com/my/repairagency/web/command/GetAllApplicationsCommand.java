package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.dto.UserWithRoleDTO;
import com.my.repairagency.repository.entity.Role;
import com.my.repairagency.repository.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllApplicationsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GetAllApplicationsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException {

        logger.trace("Command start");

        final UserWithRoleDTO user = (UserWithRoleDTO) req.getSession().getAttribute("user");
        final Role userRole = user.getRole();
        logger.trace("User {} wants to look the application's list", userRole);

        if(Role.ADMIN.equals(userRole) || Role.MANAGER.equals(userRole)){
            List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllApplications();
            req.setAttribute("applicationList", applicationList);

            List<User> masterList = UserDAO.getInstance().getAllMasters();
            req.setAttribute("masterList", masterList);
        }
        else {
            List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllUserApplication(user);
            req.setAttribute("applicationList", applicationList);
        }



       /* final int rows = applicationList.size();
        int numberOfPages = rows / 2;

        if(numberOfPages % rows > 0){
            numberOfPages++;
        }*/
        return "applicationsListSearching.jsp";
    }
}

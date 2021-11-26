package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.UserDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllApplicationsCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException {
        List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllApplications();
        req.setAttribute("applicationList", applicationList);

        List<User> masterList = UserDAO.getInstance().getAllMasters();
        req.setAttribute("masterList", masterList);
        return "applicationsListSearching.jsp";
    }
}

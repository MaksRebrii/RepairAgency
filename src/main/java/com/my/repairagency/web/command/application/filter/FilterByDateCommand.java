package com.my.repairagency.web.command.application.filter;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FilterByDateCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FilterByDateCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");
        String minValue = req.getParameter("minValue");
        String maxValue = req.getParameter("maxValue");
        System.out.println(minValue);

        List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllApplicationsByDate(minValue, maxValue);
        req.setAttribute("applicationList", applicationList);

        return "applicationsListSearching.jsp";
    }
}

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

public class FilterByMasterCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FilterByMasterCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");
        String mask = req.getParameter("mask");


        List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllApplicationsByMaster(mask);
        req.setAttribute("applicationList", applicationList);

        return "applicationsListSearching.jsp";
    }
}

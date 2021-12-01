package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import com.my.repairagency.repository.entity.CompletionStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FilterByCompletionStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FilterByCompletionStatusCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");
        String[] statuses = req.getParameterValues("completionStatus");

        List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllApplicationsByCompletionStatuses(statuses);
        req.setAttribute("applicationList", applicationList);

        return "applicationsListSearching.jsp";
    }
}

package com.my.repairagency.web.command;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.repository.ApplicationDAO;
import com.my.repairagency.repository.dto.ApplicationDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FilterByPriceCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FilterByPriceCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException {
        logger.trace("Command start");
        int minValue = Integer.parseInt(req.getParameter("minValue"));
        int maxValue = Integer.parseInt(req.getParameter("maxValue"));

        List<ApplicationDTO> applicationList = ApplicationDAO.getInstance().getAllApplicationsByPrice(minValue, maxValue);
        req.setAttribute("applicationList", applicationList);

        return "applicationsListSearching.jsp";
    }
}

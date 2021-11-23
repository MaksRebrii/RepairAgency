package com.my.repairagency.web;

import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;
import com.my.repairagency.web.command.Command;
import com.my.repairagency.web.command.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main controller. Class receives all requests.
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = process(req, resp);
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = process(req, resp);
        resp.sendRedirect(address);
    }

    private String process(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter("command");
        logger.trace("Request parameter command- -> {}", commandName);
        Command command = CommandContainer.getCommand(commandName);
        String address = "error.jsp";
        try {
            address = command.execute(req, resp);
        } catch (DAOException | EncryptException e) {
            logger.error(e.getMessage());
            return "error.jsp";
        }
        return address;
    }
}

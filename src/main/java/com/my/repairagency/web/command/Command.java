package com.my.repairagency.web.command;


import com.my.repairagency.exception.DAOException;
import com.my.repairagency.exception.EncryptException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface  Command {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, EncryptException;
}

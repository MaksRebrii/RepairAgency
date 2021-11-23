package com.my.repairagency.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//TODO rewrite filter
//@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.trace("auth filter get down to work");
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        if (session != null && session.getAttribute("user") == null)
            //req.getRequestDispatcher("login.jsp").forward(req, res);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

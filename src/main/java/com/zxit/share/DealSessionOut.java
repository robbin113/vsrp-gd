package com.zxit.share;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DealSessionOut implements Filter {

    String url = "";

    public void destroy() {

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;

        HttpServletResponse response = (HttpServletResponse) arg1;

        HttpSession session = request.getSession(false);

        if (session == null) {
            // request.getRequestDispatcher("login.jsp").
            // forward(request,response);
            String s = "/taglibs.jsp";
            request.getRequestDispatcher(s).forward(request, response);

            return;
        }
        if (request.getRequestURI().endsWith(".do")) {
            arg2.doFilter(request, response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}

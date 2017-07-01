package com.zxit.tools;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter
        implements Filter {
    private FilterConfig config = null;

    private String targetEncoding = "UTF-8";

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.targetEncoding = config.getInitParameter("encoding");
    }

    public void destroy() {
        this.config = null;
        this.targetEncoding = null;
    }

    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        request.setCharacterEncoding(this.targetEncoding);
        chain.doFilter(srequest, sresponse);
    }
}
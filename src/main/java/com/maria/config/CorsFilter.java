package com.maria.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 8/25/2017.
 */
@Component
public class CorsFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LOGGER.info("Request intercepted by the CORS filter for: " + request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin") == null ? "http://localhost:9000"
                : request.getHeader("Origin").isEmpty() ? "http://localhost:9000" : request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, X-Requested-With, X-Custom-Header, Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(200);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

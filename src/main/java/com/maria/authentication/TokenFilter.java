package com.maria.authentication;

import com.maria.security.PrincipalUser;
import com.maria.service.api.AuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 8/9/2017.
 */
public class TokenFilter extends GenericFilterBean {
    private final AuthenticationService authenticationService;
    private final AuthenticationProvider authenticationProvider;
    private final static Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

    @Autowired
    public TokenFilter(AuthenticationService authenticationService, AuthenticationProvider authenticationProvider) {
        this.authenticationService = authenticationService;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        doTokenFilter(request, response, filterChain);
    }

    private void doTokenFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");

        if (StringUtils.isBlank(token)) {
            LOGGER.info("No token provided for method: {}, URL:{}, from IP:{}, port: {}", request.getMethod(), request.getRequestURI()
                    , request.getRemoteHost(), request.getRemotePort());
            filterChain.doFilter(request, response);
            return;
        }
        LOGGER.info("Token provided: {}, from IP address {}", token, request.getRemoteAddr());

        PrincipalUser principalUser = authenticationService.authenticateByToken(token);
        TokenBasedAuthentication authentication = new TokenBasedAuthentication();
        authentication.setAuthenticated(true);
        authentication.setToken(token);
        authentication.setPrincipalUser(principalUser);
        Authentication auth = authenticationProvider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);

    }
}

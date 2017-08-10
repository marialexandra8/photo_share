package com.maria.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maria.exception.RestApiExceptionResponseJson;
import com.maria.model.http.HttpResponseCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 8/10/2017.
 */
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String errorMessage = "Acces denied";
        final PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(new RestApiExceptionResponseJson()
                .setMessage(errorMessage)
                .setCode(HttpResponseCode.UNAUTHORIZED)));
        out.flush();
    }
}

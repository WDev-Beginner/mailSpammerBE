package com.egrine.mailspammer.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public static final String UNAUTHORIZED_MESSAGE = "Authentication failed";

    public AjaxAuthenticationFailureHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.sendError(401, UNAUTHORIZED_MESSAGE);
    }
}
package io.brkn.mktimer.web.filters;

import io.brkn.mktimer.web.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessCodeFilter implements Filter {
    @Value("${mktimer.access-code}")
    private String accessCode;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String accessCode = request.getHeader("authorization");

        if (accessCode == null || accessCode.isEmpty()) {
            response.sendError(401, "Unauthorized");
        } else if (!this.accessCode.equals(accessCode)) {
            response.sendError(401, "Invalid token");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
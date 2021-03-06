package io.brkn.mktimer.web.filters;

import io.brkn.mktimer.services.TokenAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter implements Filter {
    private TokenAuthenticationService authenticationService;

    public JWTAuthenticationFilter(TokenAuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;

        try {
            Authentication authentication = authenticationService
                    .getAuthentication((HttpServletRequest) req);
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            chain.doFilter(req, resp);
        } catch (SignatureException signatureException) {
            response.sendError(401, "Unauthorized");
        } catch (MalformedJwtException malformedException) {
            response.sendError(401, "Malformed JWT token");
        } catch (ExpiredJwtException expiredJwtException) {
            response.sendError(401, "JWT token expired");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}

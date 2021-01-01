/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CredMgrApiCorsFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(CredMgrApiCorsFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("CredMgrApiCorsFilter doFilter()");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader(
                "Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With, authorization");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("CORS Filter Destroyed");
    }
}

package org.xinyue.xsecurity.web.filter;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

//@Component
public class LogFilter implements Filter {
    private Log log = LogFactory.getLog(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Log Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Log filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Log filter spend:"+ (System.currentTimeMillis() - start));
        log.info("Log filter end");
    }

    @Override
    public void destroy() {
        log.info("Log Filter destroy");
    }
}

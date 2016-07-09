package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by user on 30.06.2016.
 */
public class LocaleServletFilter implements Filter {

    private static final String LOCALE_PARAM_NAME = "locale";

    private String locale;


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        req.setAttribute(LOCALE_PARAM_NAME, locale);
        filterChain.doFilter(req, res);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
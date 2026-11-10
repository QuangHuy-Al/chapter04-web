package com.example.filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class PageCacheFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getServletPath();
        if (path.isEmpty() || path.endsWith("/") || path.endsWith(".html") || path.endsWith(".jsp")) {
            ((HttpServletResponse) response).setHeader("Cache-Control", "no-store");
        }
        chain.doFilter(request, response);
    }
}

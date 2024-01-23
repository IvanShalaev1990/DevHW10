package org.exemple.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getParameter("timezone") == null){
            chain.doFilter(req, resp);
        }
        try {
            String timezone = req.getParameter("timezone");
            ZoneId.of(timezone);
            chain.doFilter(req, resp);
        }catch (Exception exception){
            resp.setStatus(400);
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().write("Invalid timezone");
            resp.getWriter().close();
        }

    }
}

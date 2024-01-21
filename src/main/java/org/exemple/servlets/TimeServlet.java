package org.exemple.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(value = "/time", name = "timeServlet")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(LocalDateTime
                .now(ZoneId.of("UTC"))
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + getUTC(getQuery(req)));
        resp.getWriter().close();
    }

    private String getQuery(HttpServletRequest req) {
        Optional<String> optionalQuery = Optional.ofNullable(req.getQueryString());
        return optionalQuery.orElseGet(() -> "timezone=UTC");
    }

    private String getUTC(String query) {
        String[] queryKeyValue = query.split("=");
        return queryKeyValue[1];
    }
}

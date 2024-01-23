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
        resp.getWriter().write(getResponse(req));
        resp.getWriter().close();
    }
//ZoneId.of("UTC")
    private String getResponse(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now(ZoneId.of(getQuery(req))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        sb.append(" ");
        sb.append(getQuery(req));
        return  sb.toString();
    }

    private String getQuery(HttpServletRequest req) {
        Optional<String> optionalQuery = Optional.ofNullable(req.getParameter("timezone"));
        return optionalQuery.orElseGet(() -> "UTC");
    }
}

package com.example.servlet;

import com.example.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/email/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (!"add".equals(request.getParameter("action"))) {
            doGet(request, response);
            return;
        }

        User user = new User(parameter(request, "firstName"),
                parameter(request, "lastName"), parameter(request, "email"));
        List<String> errors = new ArrayList<>();
        if (user.getFirstName().isBlank() || user.getFirstName().length() > 80) {
            errors.add("Please enter a first name of 1 to 80 characters.");
        }
        if (user.getLastName().isBlank() || user.getLastName().length() > 80) {
            errors.add("Please enter a last name of 1 to 80 characters.");
        }
        if (user.getEmail().length() > 254
                || !user.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            errors.add("Please enter a valid email address.");
        }

        if (errors.isEmpty()) {
            request.setAttribute("user", user);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errors", errors);
        }
        request.getRequestDispatcher("/email/thanks.jsp").forward(request, response);
    }

    private static String parameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value.strip();
    }
}

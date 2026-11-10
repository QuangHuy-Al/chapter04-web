package com.example.servlet;

import com.example.model.Survey;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {
    private static final Set<String> SOURCES = Set.of(
            "Search engine", "Word of mouth", "Social Media", "Other");
    private static final Set<String> CONTACT_METHODS = Set.of(
            "Email or postal mail", "Email only", "Postal mail only");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Survey survey = new Survey(
                parameter(request, "firstName"), parameter(request, "lastName"),
                parameter(request, "email"), parameter(request, "dateOfBirth"),
                parameter(request, "heardFrom"),
                "yes".equals(request.getParameter("wantsUpdates")),
                "yes".equals(request.getParameter("emailAnnouncements")),
                parameter(request, "contactVia"));

        List<String> errors = validate(survey);
        if (!errors.isEmpty()) {

            request.setAttribute("errors", errors);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/survey-result.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("survey", survey);
        response.sendRedirect(response.encodeRedirectURL(
                request.getContextPath() + "/survey-result.jsp"));
    }

    private static String parameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value.strip();
    }

    private static List<String> validate(Survey survey) {
        List<String> errors = new ArrayList<>();
        if (survey.getFirstName().isBlank() || survey.getFirstName().length() > 80) {
            errors.add("Please enter a first name of 1 to 80 characters.");
        }
        if (survey.getLastName().isBlank() || survey.getLastName().length() > 80) {
            errors.add("Please enter a last name of 1 to 80 characters.");
        }
        if (survey.getEmail().length() > 254
                || !survey.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            errors.add("Please enter a valid email address.");
        }
        try {
            if (LocalDate.parse(survey.getDateOfBirth()).isAfter(LocalDate.now())) {
                errors.add("Date of birth cannot be in the future.");
            }
        } catch (DateTimeParseException exception) {
            errors.add("Please enter a valid date of birth.");
        }
        if (!SOURCES.contains(survey.getHeardFrom())) {
            errors.add("Please select how you heard about us.");
        }
        if (!CONTACT_METHODS.contains(survey.getContactVia())) {
            errors.add("Please select a contact method.");
        }
        return errors;
    }
}

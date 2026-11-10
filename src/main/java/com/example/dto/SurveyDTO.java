package com.example.dto;

import com.example.model.Survey;

import java.io.Serializable;

public class SurveyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String dateOfBirth;
    private final String heardFrom;
    private final boolean wantsUpdates;
    private final boolean emailAnnouncements;
    private final String contactVia;

    public SurveyDTO(Survey survey) {
        this.firstName = survey.getFirstName();
        this.lastName = survey.getLastName();
        this.email = survey.getEmail();
        this.dateOfBirth = survey.getDateOfBirth();
        this.heardFrom = survey.getHeardFrom();
        this.wantsUpdates = survey.isWantsUpdates();
        this.emailAnnouncements = survey.isEmailAnnouncements();
        this.contactVia = survey.getContactVia();
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getHeardFrom() {
        return heardFrom;
    }
    public boolean isWantsUpdates() {
        return wantsUpdates;
    }
    public boolean isEmailAnnouncements() {
        return emailAnnouncements;
    }
    public String getContactVia() {
        return contactVia;
    }
}

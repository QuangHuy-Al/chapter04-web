package com.example.model;

public class Survey {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String dateOfBirth;
    private final String heardFrom;
    private final boolean wantsUpdates;
    private final boolean emailAnnouncements;
    private final String contactVia;

    public Survey(String firstName, String lastName, String email,
                  String dateOfBirth, String heardFrom, boolean wantsUpdates,
                  boolean emailAnnouncements, String contactVia) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.heardFrom = heardFrom;
        this.wantsUpdates = wantsUpdates;
        this.emailAnnouncements = emailAnnouncements;
        this.contactVia = contactVia;
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

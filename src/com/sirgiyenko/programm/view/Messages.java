package com.sirgiyenko.programm.view;

public enum Messages {
    
    NAME("Pls, enter name"),
    AGE("Pls., enter age"),
    CORRECT_AGE("Pls., enter correct age (between 1 and 90 years)"),
    PHONE_NUMBER("Pls., enter phone number"),
    CORRECT_PHONE_NUMBER("Pls., enter correct phone number (with ___ digits as minimum)"),
    CREATION(" is successfully created"),
    NO_CREATION(" has already existed in address book and can't be created again"),
    EMPTY_BOOK("There are no contacts in address book"),
    NO_CONTACT("No such contact in address book"),
    REMOVAL(" is successfully deleted");

    private String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

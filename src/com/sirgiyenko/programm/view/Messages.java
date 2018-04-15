package com.sirgiyenko.programm.view;

public enum Messages {
    
    NAME ("Pls, enter name"),
    AGE ("Pls., enter age"),
    CORRECTAGE ("Pls., enter correct age (between 1 and 90 years)"),
    PHONENUMBER("Pls., enter phone number"),
    CORRECTPHONENMBER("Pls., enter correct phone number (with ___ digits as minimum)"),
    CREATION(" is successfully created"),
    NOCREATION(" has already existed in address book and can't be created again"),
    EMPTYBOOK("There are no contacts in address book"),
    NOCONTACT("No such contact in address book"),
    REMOVAL(" is successfully deleted");

    private String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

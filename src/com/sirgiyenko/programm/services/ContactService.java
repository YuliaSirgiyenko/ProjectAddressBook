package com.sirgiyenko.programm.services;

import com.sirgiyenko.programm.model.Contact;

public interface ContactService {

    /*Creation of contact with input parameters.*/
    boolean createContact(String name, int age, long phoneNumber);

    /*Removal of contact with definite parameter from contact base.*/
    boolean deleteContact(String name);

    /*Show all contact base.*/
    void showContactList();

    /*Search of contact based on definite input parameter.*/
    Contact searchContact(String name);

    /*Editing contact.*/
    Contact editContact(Contact contact, String name);
    Contact editContact(Contact contact, int age);
    Contact editContact(Contact contact, long phoneNumber);

}

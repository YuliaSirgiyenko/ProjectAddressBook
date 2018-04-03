package com.sirgiyenko.programm.services;

import com.sirgiyenko.programm.model.Contact;

public interface ContactService {

    void createContact(String name, int age, long phoneNumber);
    void deleteContact(String name);
    void showContactList();
    Contact searchContact(String name);

}

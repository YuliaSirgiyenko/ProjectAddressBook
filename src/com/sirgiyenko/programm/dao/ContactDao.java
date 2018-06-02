package com.sirgiyenko.programm.dao;

import com.sirgiyenko.programm.model.Contact;

import java.io.IOException;
import java.util.List;

/*Interface describes behavior of work with different types of data keeping such as:
* file system, databases, etc.*/

public interface ContactDao {

    void saveContact(Contact contact);

    List<Contact> showContactList();

    Contact searchContact(String name);

    void removeContact(String name);

    Contact editContact(Contact contact, String name);
    Contact editContact(Contact contact, int age);
    Contact editContact(Contact contact, long phoneNumber);
}

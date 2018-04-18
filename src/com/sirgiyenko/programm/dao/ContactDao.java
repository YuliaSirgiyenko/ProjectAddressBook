package com.sirgiyenko.programm.dao;

import com.sirgiyenko.programm.model.Contact;

import java.io.IOException;

/*Interface describes behavior of work with different types of data keeping such as:
* file system, databases, etc.*/

public interface ContactDao {

    void saveContact(Contact contact);

    void showContactList();

    Contact searchContact(String name);

    void removeContact(String name);
}

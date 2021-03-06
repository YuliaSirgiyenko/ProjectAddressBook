package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.view.Messages;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/*Realization of Contact Service which use Array List for data keeping.*/

public class ContactServiceImpl implements ContactService {

    private List<Contact> contactList = new ArrayList<>();

    @Override
    public boolean createContact(String name, int age, long phoneNumber) {
        this.contactList.add(new Contact(name, age, phoneNumber));
        return true;
    }

    @Override
    public void showContactList() {
        if (contactList.isEmpty()) {
            System.out.println(Messages.EMPTY_BOOK.getText());
        } else {
            for (Contact contact : this.contactList) {
                System.out.println(contact);
            }
        }
    }

    @Override
    /*Not used for realization via console.*/
    public ObservableList showContactsFX() {
        return null;
    }

    @Override
    public Contact searchContact(String name) {
        Contact searchResult = null;
        for (Contact contact : this.contactList) {
            if (contact.getName().equals(name)) {
                searchResult = contact;
                break;
            }
        }

        return searchResult;
    }

    @Override
    public Contact editContact(Contact contact, String name) {
        contact.setName(name);
        return contact;
    }

    @Override
    public Contact editContact(Contact contact, int age) {
        contact.setAge(age);
        return contact;
    }

    @Override
    public Contact editContact(Contact contact, long phoneNumber) {
        contact.setPhoneNumber(phoneNumber);
        return contact;
    }

    @Override
    /*Not used for realization via console.*/
    public int getBookSize() {
        return 0;
    }

    @Override
    public boolean deleteContact(String name) {
        boolean flag = false;
        if (searchContact(name) != null){
            contactList.remove(searchContact(name));
            flag = true;
        }
        return flag;
    }

}

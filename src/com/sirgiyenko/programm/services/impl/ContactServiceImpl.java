package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    private List<Contact> contactList = new ArrayList<>();

    @Override
    public void createContact(String name, int age, long phoneNumber) {
        this.contactList.add(new Contact(name, age, phoneNumber));
        System.out.println("Contact for '" + name + "' is successfully created.");
    }

    @Override
    public void showContactList() {
        for (Contact contact : this.contactList) {
            System.out.println(contact);
        }
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
    public void showContact(Contact contact, String name) {
        if (contact == null) {
            System.out.println("No contact with name '" + name + "' in address book");
        } else {
            System.out.println(contact);
        }
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
    public void deleteContact(String name) {
        if (searchContact(name) != null){
            contactList.remove(searchContact(name));
            System.out.println("Contact with name '" + name + "' is successfully deleted");
        } else {
            showContact(searchContact(name), name);
        }
    }

}

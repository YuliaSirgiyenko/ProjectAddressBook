package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.view.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactServiceImplFX implements ContactService {

    private ObservableList<Contact> contactList;

    public ContactServiceImplFX(){
        this.contactList = FXCollections.observableArrayList();
    }

    @Override
    public int getBookSize(){
        int i = 0;
        for (Contact contact : this.contactList) {
            i++;
            }
        return i;
    }

    @Override
    public ObservableList<Contact> showContactsFX() {
        return contactList;
    }

    @Override
    public boolean createContact(String name, int age, long phoneNumber) {
        this.contactList.add(new Contact(name, age, phoneNumber));
        return true;
    }

    @Override
    /*Not realized for FX realization.*/
    public void showContactList() {
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
    public boolean deleteContact(String name) {
        boolean flag = false;
        if (searchContact(name) != null){
            contactList.remove(searchContact(name));
            flag = true;
        }
        return flag;
    }


}

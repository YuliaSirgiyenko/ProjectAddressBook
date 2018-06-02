package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.dao.ContactDao;
import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FScontactServiceImplFX implements ContactService {

    private final ContactDao contactDao;
    ObservableList<Contact> List = FXCollections.observableArrayList();

    public FScontactServiceImplFX(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public boolean createContact(String name, int age, long phoneNumber) {
        contactDao.saveContact(new Contact(name, age, phoneNumber));
        showContactList();
        return true;
    }

    @Override
    public boolean deleteContact(String name) {
        boolean flag = false;
        if (searchContact(name) != null){
            contactDao.removeContact(name);
            flag = true;
        }
        return flag;
    }

    /*Not realized for FX realization.*/
    @Override
    public void showContactList() {
    }

    @Override
    public ObservableList showContactsFX() {
        List.clear();
        List.addAll(contactDao.showContactList());
        return List;
    }

    @Override
    public Contact searchContact(String name) {
        return contactDao.searchContact(name);
    }

    @Override
    public Contact editContact(Contact contact, String name) {
        return contactDao.editContact(contact, name);
    }

    @Override
    public Contact editContact(Contact contact, int age) {
        return contactDao.editContact(contact, age);
    }

    @Override
    public Contact editContact(Contact contact, long phoneNumber) {
        return contactDao.editContact(contact, phoneNumber);
    }

    @Override
    public int getBookSize() {
        List.clear();
        List.addAll(contactDao.showContactList());

        int i = 0;
        for (Contact contact : List) {
            i++;
        }
        return i;
    }


}

package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.dao.ContactDao;
import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;

/*Realization of Contact Service which use File System for data keeping.*/

public class FSContactServiceImpl implements ContactService {

    private final ContactDao contactDao;

    public FSContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public boolean createContact(String name, int age, long phoneNumber) {
        contactDao.saveContact(new Contact(name, age, phoneNumber));
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

    @Override
    public void showContactList() {
        contactDao.showContactList();
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
}

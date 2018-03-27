package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.services.ContactService;

public class ContactServiceImpl implements ContactService {

    @Override
    public boolean contactCreation(String name, int phoneNumber) {
        return false;
    }

    @Override
    public boolean contactModification(String name, int phoneNumber) {
        return false;
    }

    @Override
    public boolean contactSerach(String name) {
        return false;
    }

    @Override
    public boolean contactRemoval(String name) {
        return false;
    }
}

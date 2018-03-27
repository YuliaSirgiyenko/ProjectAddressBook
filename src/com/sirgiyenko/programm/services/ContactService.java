package com.sirgiyenko.programm.services;

public interface ContactService {

    /*Method for creation of new contact.
    * May be overloaded.*/
    boolean contactCreation(String name, int phoneNumber);

    /*Method for modification of existing contact.
     * May be overloaded.*/
    boolean contactModification(String name, int phoneNumber);

    /*Method for search of contact.
     * May be overloaded.*/
    boolean contactSerach(String name);

    /*Method for removal of contact.
     * May be overloaded.*/
    boolean contactRemoval(String name);

}

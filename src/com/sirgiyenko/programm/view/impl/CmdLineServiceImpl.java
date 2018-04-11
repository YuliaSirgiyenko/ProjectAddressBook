package com.sirgiyenko.programm.view.impl;

import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.services.ValidatorService;
import com.sirgiyenko.programm.view.CmdLineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdLineServiceImpl implements CmdLineService{

    private ContactService contactService;
    private ValidatorService validatorService;
    //Object BufferedReader for reading from console thanks to object new InputStreamReader(System.in).
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public CmdLineServiceImpl(ContactService contactService, ValidatorService validatorService) {
        this.contactService = contactService;
        this.validatorService = validatorService;
    }

    public static void showMenu() {
        System.out.println("Pls., choose menu point");
        System.out.println("1. Create Contact");
        System.out.println("2. Show all Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Edit Contact");
        System.out.println("5. Delete Contact");
        System.out.println("0. Exit");
    }

    @Override
    public void runMenu() throws IOException {
        boolean isRunning = true;

        while(isRunning) {
            showMenu();
            String line = br.readLine();

            switch(line){
                case "1":
                    createContact();
                    break;
                case "2":
                    showContactList();
                    break;
                case "3":
                    searchContact();
                    break;
                case "4":
                    editContact();
                    break;
                case "5":
                    deleteContact();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pls., choose menu point");
                    break;
            }

        }
    }

    private void deleteContact() throws IOException {
        System.out.println("Pls., enter name of Contact for removal from address book");
        String name = br.readLine();
        contactService.deleteContact(name);
    }

    private void showContactList(){
        contactService.showContactList();
    }

    private void searchContact() throws IOException {
        System.out.println("Pls., enter name of Contact for search");
        String name = br.readLine();
        contactService.showContact(contactService.searchContact(name), name);
    }

    private void editContact () throws IOException {
        System.out.println("Pls., enter name of Contact for editing");
        String name = br.readLine();
        Contact contact;

        if (contactService.searchContact(name) == null) {
            contactService.showContact(contactService.searchContact(name), name);
        } else {
            contact = contactService.searchContact(name);

            System.out.println("Do you want to change name (Y/N)");
            String answer = br.readLine();

            if (answer.equalsIgnoreCase("Y")) {
                System.out.println("Pls., enter new name");
                String newName = br.readLine();
                contact = contactService.editContact(contact, newName);
            }

            System.out.println("Do you want to change age (Y/N)");
            answer = br.readLine();

            if (answer.equalsIgnoreCase("Y")) {
                int newAge = validatorService.readAge();
                contact = contactService.editContact(contact, newAge);
            }

            System.out.println("Do you want to change phoneNumber (Y/N)");
            answer = br.readLine();

            if (answer.equalsIgnoreCase("Y")) {
                long newPhoneNumber = validatorService.readPhoneNumber();
                contact = contactService.editContact(contact, newPhoneNumber);
            }

            System.out.println("Contact after editing " + contact.toString());

        }

    }

    private void createContact() throws IOException {
        System.out.println("Pls., enter name");
        String name = br.readLine();

        if (contactService.searchContact(name) != null) {
            System.out.println("Contact with name '" + name + "' has already existed in address book and " +
                    "can't be created again");
        } else {
            int age = validatorService.readAge();
            long phoneNumber = validatorService.readPhoneNumber();
            contactService.createContact(name, age, phoneNumber);
        }

    }

}

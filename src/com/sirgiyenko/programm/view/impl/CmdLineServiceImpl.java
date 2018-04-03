package com.sirgiyenko.programm.view.impl;

import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.services.impl.ContactServiceImpl;
import com.sirgiyenko.programm.view.CmdLineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdLineServiceImpl implements CmdLineService{

    private ContactService contactService;
    //Object BufferedReader for reading from console thanks to object new InputStreamReader(System.in).
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public CmdLineServiceImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    public static void showMenu() {
        System.out.println("Pls., choose menu point");
        System.out.println("1. Create Contact");
        System.out.println("2. Show all Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Delete Contact");
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
        System.out.println("Pls., enter name of Contract for search");
        String name = br.readLine();
        contactService.deleteContact(name);
    }

    private void showContactList(){
        contactService.showContactList();
    }

    private void searchContact() throws IOException {
        System.out.println("Pls., enter name of Contract for search");
        String name = br.readLine();
        if (contactService.searchContact(name) == null) {
            System.out.println("No contact with name '" + name + "' in address book");
        } else {
            System.out.println(contactService.searchContact(name));
        }
    }

    private void createContact() throws IOException {
        System.out.println("Pls., enter name");
        String name = br.readLine();

        if (contactService.searchContact(name) != null) {
            System.out.println("Contact with name '" + name + "' has already existed in address book and " +
                    "can't be created again");
        } else {

            System.out.println("Pls., enter age");
            int age = 0;
            boolean ageFormat = false;
            while (!ageFormat) {
                try {
                    age = Integer.parseInt(br.readLine());
                    ageFormat = true;
                } catch (NumberFormatException e) {
                    System.out.println("Pls., enter correct age");
                }
            }

            System.out.println("Pls., enter phone number");
            long phoneNumber = 0;
            boolean phoneFormat = false;
            while (!phoneFormat) {
                try {
                    phoneNumber = Long.parseLong(br.readLine());
                    phoneFormat = true;
                } catch (NumberFormatException e) {
                    System.out.println("Pls., enter correct phone number");
                }
            }

            contactService.createContact(name, age, phoneNumber);
        }

    }

}

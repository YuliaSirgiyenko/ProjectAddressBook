package com.sirgiyenko.programm.view.impl;

import com.sirgiyenko.programm.businessException.IncorrectValueException;
import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.util.ValidatorUtilImpl;
import com.sirgiyenko.programm.view.CmdLineService;
import com.sirgiyenko.programm.view.Messages;

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

    private static void showMainMenu() {
        System.out.println("Pls., choose menu point");
        System.out.println("1. Create Contact");
        System.out.println("2. Show all Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Edit Contact");
        System.out.println("5. Delete Contact");
        System.out.println("0. Exit");
    }

    @Override
    public void runMainMenu() throws IOException {
        boolean isRunning = true;

        while(isRunning) {
            showMainMenu();
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
        System.out.println(Messages.NAME.getText());
        String name = br.readLine();

        if (contactService.deleteContact(name)){
            System.out.println("Contact " + name + Messages.REMOVAL.getText());
        } else {
            System.out.println(Messages.NO_CONTACT.getText());
        }
    }

    private void showContactList(){
        contactService.showContactList();
    }

    private void searchContact() throws IOException {
        System.out.println(Messages.NAME.getText());
        String name = br.readLine();
        if (contactService.searchContact(name) == null) {
            System.out.println(Messages.NO_CONTACT.getText());
        } else {
            System.out.println(contactService.searchContact(name));
        }
    }

    private void editContact () throws IOException {
        System.out.println(Messages.NAME.getText());
        String name = br.readLine();
        Contact contact = contactService.searchContact(name);

        if (contact == null) {
            System.out.println(Messages.NO_CONTACT.getText());
        } else {
            System.out.println(contact);
            System.out.println("Do you want to change name (Y/N)");
            if (br.readLine().equalsIgnoreCase("Y")) {
                System.out.println("Pls., enter new name");
                String newName = br.readLine();
                contact = contactService.editContact(contact, newName);
            }

            System.out.println("Do you want to change age (Y/N)");
            if (br.readLine().equalsIgnoreCase("Y")) {
                int age = readAge();
                contact = contactService.editContact(contact, age);
            }

            System.out.println("Do you want to change phoneNumber (Y/N)");
            if (br.readLine().equalsIgnoreCase("Y")){
                long phoneNumber = readPhoneNumber();
                contact = contactService.editContact(contact, phoneNumber);
            }

            System.out.println("Contact after editing " + contact);
        }
    }

    private void createContact() throws IOException {
        System.out.println(Messages.NAME.getText());
        String name = br.readLine();

        if (contactService.searchContact(name) != null) {
            System.out.println("Contact " + name + Messages.NO_CREATION.getText());
        } else {
            int age = readAge();
            long phoneNumber = readPhoneNumber();
            if (contactService.createContact(name, age, phoneNumber)){
                System.out.println("Contact " + name + Messages.CREATION.getText());
            }
        }

    }

    private int readAge() throws IOException {
        boolean temp = false;

        System.out.println(Messages.AGE.getText());
        int age = 0;
        while (!temp) {
            try {
                age = ValidatorUtilImpl.checkAge(br.readLine());
                temp = true;
            } catch (NumberFormatException | IncorrectValueException e) {
                System.out.println(Messages.CORRECT_AGE.getText());
            }
        }

        return age;
    }

    private long readPhoneNumber() throws IOException {
        boolean temp = false;

        System.out.println(Messages.PHONE_NUMBER.getText());
        long phoneNumber = 0;
        while (!temp) {
            try {
                phoneNumber = ValidatorUtilImpl.checkPhoneNumber(br.readLine());
                temp = true;
            } catch (NumberFormatException | IncorrectValueException e) {
                System.out.println(Messages.CORRECT_PHONE_NUMBER.getText());
            }
        }

        return phoneNumber;
    }

}

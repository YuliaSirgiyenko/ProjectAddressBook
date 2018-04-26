package com.sirgiyenko.programm.dao.impl;

import com.sirgiyenko.programm.dao.ContactDao;
import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.view.Messages;

import java.io.*;

public class FileSystemContactDao implements ContactDao {

    private static final File FILE = new File("addressBook");

    public FileSystemContactDao(){
        if (!FILE.exists()){
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveContact(Contact contact) {

        try (FileWriter writer = new FileWriter(FILE, true)){
            writer.write(contact.toString() + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showContactList() {

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            if ((line = reader.readLine()) == null) {
                System.out.println(Messages.EMPTY_BOOK.getText());
            } else {
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact searchContact(String name) {
        Contact searchResult = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            String line;
            while ((line = reader.readLine()) != null) {
                int startIndex = line.indexOf('\'', 0);
                int endIndex = line.indexOf('\'', startIndex+1);
                String contactName = line.substring(startIndex+1, endIndex);

                if (contactName.equals(name)){
                    startIndex = line.indexOf('\'', endIndex+1);
                    endIndex = line.indexOf('\'', startIndex+1);
                    int age = Integer.parseInt(line.substring(startIndex+1, endIndex));

                    startIndex = line.indexOf('\'', endIndex+1);
                    endIndex = line.indexOf('\'', startIndex+1);
                    long phoneNumber = Long.parseLong(line.substring(startIndex+1, endIndex));

                    searchResult = new Contact(name, age, phoneNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResult;
    }

    @Override
    public void removeContact(String name) {
        File tempFile = new File("temp");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE));
             FileWriter writer = new FileWriter(tempFile, true)){

            String line;
            while ((line = reader.readLine()) != null) {

                int startIndex = line.indexOf('\'', 0);
                int endIndex = line.indexOf('\'', startIndex+1);
                String contactName = line.substring(startIndex+1, endIndex);

                if (!contactName.equals(name)) {
                    writer.write(line);
                    writer.write("\n");
                    writer.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FILE.delete();
        tempFile.renameTo(new File("addressBook"));

    }

    @Override
    public Contact editContact(Contact contact, String newName) {
        File tempFile = new File("temp");
        String oldName = contact.getName();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE));
             FileWriter writer = new FileWriter(tempFile, true)){

            String line;
            while ((line = reader.readLine()) != null) {

                int startIndex = line.indexOf('\'', 0);
                int endIndex = line.indexOf('\'', startIndex+1);
                String contactName = line.substring(startIndex+1, endIndex);

                if (!contactName.equals(oldName)) {
                    writer.write(line);
                    writer.write("\n");
                    writer.flush();
                }
                if (contactName.equals(oldName)) {
                    writer.write(line.substring(0, startIndex+1));
                    String tempRestLine = line.substring(endIndex, line.length());
                    writer.write(newName);
                    writer.write(tempRestLine);
                    writer.write("\n");
                    writer.flush();
                    contact.setName(newName);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FILE.delete();
        tempFile.renameTo(new File("addressBook"));

        return contact;
    }

    @Override
    public Contact editContact(Contact contact, int newAge) {
        File tempFile = new File("temp");
        String name = contact.getName();
        String oldAge = Integer.toString(contact.getAge());

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE));
             FileWriter writer = new FileWriter(tempFile, true)){

            String line;
            while ((line = reader.readLine()) != null) {

                int startIndex = line.indexOf('\'', 0);
                int endIndex = line.indexOf('\'', startIndex+1);
                String contactName = line.substring(startIndex+1, endIndex);

                if (!contactName.equals(name)) {
                    writer.write(line);
                    writer.write("\n");
                    writer.flush();
                }
                if (contactName.equals(name)) {
                    startIndex = line.indexOf(oldAge, endIndex+1);
                    writer.write(line.substring(0, startIndex));
                    String tempRestLine = line.substring(startIndex+oldAge.length(), line.length());
                    writer.write(Integer.toString(newAge));
                    writer.write(tempRestLine);
                    writer.write("\n");
                    writer.flush();
                    contact.setAge(newAge);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FILE.delete();
        tempFile.renameTo(new File("addressBook"));

        return contact;
    }

    @Override
    public Contact editContact(Contact contact, long newPhoneNumber) {
        File tempFile = new File("temp");
        String name = contact.getName();
        String oldPhoneNumber = Long.toString(contact.getPhoneNumber());

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE));
             FileWriter writer = new FileWriter(tempFile, true)){

            String line;
            while ((line = reader.readLine()) != null) {

                int startIndex = line.indexOf('\'', 0);
                int endIndex = line.indexOf('\'', startIndex+1);
                String contactName = line.substring(startIndex+1, endIndex);

                if (!contactName.equals(name)) {
                    writer.write(line);
                    writer.write("\n");
                    writer.flush();
                }
                if (contactName.equals(name)) {
                    startIndex = line.indexOf(oldPhoneNumber, endIndex+1);
                    writer.write(line.substring(0, startIndex));
                    String tempRestLine = line.substring(startIndex+oldPhoneNumber.length(), line.length());
                    writer.write(Long.toString(newPhoneNumber));
                    writer.write(tempRestLine);
                    writer.write("\n");
                    writer.flush();
                    contact.setPhoneNumber(newPhoneNumber);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FILE.delete();
        tempFile.renameTo(new File("addressBook"));

        return contact;
    }

}

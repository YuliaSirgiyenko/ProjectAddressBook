package com.sirgiyenko.programm.dao.impl;

import com.sirgiyenko.programm.dao.ContactDao;
import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.view.Messages;

import java.io.*;

public class FileSystemContactDaoImpl implements ContactDao {

    private static final File FILE = new File("addressBook");

    static {
        if (!FILE.exists()){
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileSystemContactDaoImpl(){
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
                System.out.println(Messages.EMPTYBOOK.getText());
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

}

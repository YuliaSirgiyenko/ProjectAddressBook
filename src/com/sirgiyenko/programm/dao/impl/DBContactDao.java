package com.sirgiyenko.programm.dao.impl;

import com.sirgiyenko.programm.dao.ContactDao;
import com.sirgiyenko.programm.model.Contact;

import java.sql.*;

public class DBContactDao implements ContactDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/AddressBook";
    private static final String USER = "Yulia";
    private static final String PASS = "yu";

    public DBContactDao(){

        //Connect driver.
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Establish connection, create statement object for sql requests generation.
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement st = connection.createStatement();) {

            st.execute("CREATE TABLE IF NOT EXISTS AddressBook(id INT NOT NULL AUTO_INCREMENT" +
                    " PRIMARY KEY, name VARCHAR(255), age INT, phoneNumber BIGINT);");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement st = connection.prepareStatement("INSERT INTO AddressBook VALUES(NULL, ?, ?, ?);");) {

            st.setString(1, contact.getName());
            st.setInt(2, contact.getAge());
            st.setLong(3, contact.getPhoneNumber());
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContactList() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement st = connection.createStatement();) {

            ResultSet rs = st.executeQuery("SELECT * FROM AddressBook;");
            while(rs.next()){
                Contact contact = new Contact(rs.getString("name"), rs.getInt("age"),
                        rs.getLong("phoneNumber"));
                System.out.println(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact searchContact(String name) {
        return null;
    }

    @Override
    public void removeContact(String name) {

    }

    @Override
    public Contact editContact(Contact contact, String name) {
        return null;
    }

    @Override
    public Contact editContact(Contact contact, int age) {
        return null;
    }

    @Override
    public Contact editContact(Contact contact, long phoneNumber) {
        return null;
    }
}

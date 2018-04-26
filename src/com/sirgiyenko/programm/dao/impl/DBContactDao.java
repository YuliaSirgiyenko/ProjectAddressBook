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
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact searchContact(String name) {
        Contact searchResult = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement st = connection.createStatement();) {

            ResultSet rs = st.executeQuery("SELECT * FROM AddressBook WHERE name = '" + name + "';");
            while(rs.next()){
                searchResult = new Contact(rs.getString("name"), rs.getInt("age"),
                        rs.getLong("phoneNumber"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResult;
    }

    @Override
    public void removeContact(String name) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement st = connection.prepareStatement
                     ("DELETE FROM AddressBook WHERE name = ?;");) {

            st.setString(1, name);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Contact editContact(Contact contact, String name) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement st = connection.prepareStatement
                     ("UPDATE AddressBook SET name = ? WHERE name = ?;");) {

            st.setString(1, name);
            st.setString(2, contact.getName());
            st.executeUpdate();
            contact.setName(name);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public Contact editContact(Contact contact, int age) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement st = connection.prepareStatement
                     ("UPDATE AddressBook SET age = ? WHERE name = ?;");) {

            st.setInt(1, age);
            st.setString(2, contact.getName());
            st.executeUpdate();
            contact.setAge(age);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public Contact editContact(Contact contact, long phoneNumber) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement st = connection.prepareStatement
                     ("UPDATE AddressBook SET phoneNumber = ? WHERE name = ?;");) {

            st.setLong(1, phoneNumber);
            st.setString(2, contact.getName());
            st.executeUpdate();
            contact.setPhoneNumber(phoneNumber);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }
}

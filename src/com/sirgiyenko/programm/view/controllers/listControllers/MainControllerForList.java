package com.sirgiyenko.programm.view.controllers.listControllers;

import com.sirgiyenko.programm.model.Contact;
import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.services.impl.ContactServiceImplFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class MainControllerForList {

    private ContactService contactService;
    private Contact tempContact;

    public MainControllerForList() {
        this.contactService = new ContactServiceImplFX();
    }

    @FXML
    private Button createContactButton;

    @FXML
    private Button editContactButton;

    @FXML
    private Button searchContactButton;

    @FXML
    private Button deleteContactButton;

    @FXML
    private TableView<Contact> contactTable;

    @FXML
    private TableColumn<Contact, String> columnName;

    @FXML
    private TableColumn<Contact, Integer> columnAge;

    @FXML
    private TableColumn<Contact, Long> columnPhoneNumber;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputAge;

    @FXML
    private TextField inputPhoneNumber;

    @FXML
    private Label textName;

    @FXML
    private Label textAge;

    @FXML
    private Label textPhoneNumber;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea textMessage;

    @FXML
    private Button searchButton;

    @FXML
    private Button searchAndEditButton;

    @FXML
    private Button editButton;

    @FXML
    private TextArea resultMessage;

    @FXML
    public void createContact(){
        textMessage.setVisible(false);

        textName.setVisible(true);
        textName.setText("Insert name");
        inputName.setVisible(true);
        inputName.clear();

        textAge.setVisible(true);
        textAge.setText("Insert age");
        inputAge.setVisible(true);
        inputAge.clear();

        textPhoneNumber.setVisible(true);
        textPhoneNumber.setText("Insert phone number");
        inputPhoneNumber.setVisible(true);
        inputPhoneNumber.clear();

        createButton.setVisible(true);
        searchButton.setVisible(false);
        searchAndEditButton.setVisible(false);
        editButton.setVisible(false);
        resultMessage.setVisible(false);

        contactTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void create(){
        String name = inputName.getText();
        String age = inputAge.getText();
        String phoneNumber = inputPhoneNumber.getText();
        contactService.createContact(name, new Integer(age), new Long (phoneNumber));
        initialize();

        textName.setVisible(false);
        inputName.setVisible(false);

        textAge.setVisible(false);
        inputAge.setVisible(false);

        textPhoneNumber.setVisible(false);
        inputPhoneNumber.setVisible(false);

        createButton.setVisible(false);
        editContactButton.setDisable(false);
        searchContactButton.setDisable(false);
        deleteContactButton.setDisable(false);
        searchButton.setVisible(false);
        searchAndEditButton.setVisible(false);
        editButton.setVisible(false);

        resultMessage.setVisible(false);
    }

    @FXML
    public void searchContact(){
        textName.setText("Insert name for search");
        textName.setVisible(true);
        inputName.clear();
        inputName.setVisible(true);

        textAge.setVisible(false);
        inputAge.setVisible(false);
        textPhoneNumber.setVisible(false);
        inputPhoneNumber.setVisible(false);

        searchButton.setVisible(true);
        deleteButton.setVisible(false);
        searchAndEditButton.setVisible(false);
        editButton.setVisible(false);
        resultMessage.setVisible(false);

        contactTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void search(){
        String name = inputName.getText();
        Contact searchResult = contactService.searchContact(name);
        if (searchResult == null) {
            resultMessage.setVisible(true);
            resultMessage.setText("There is no contact with name '" + name + "' in address book.");
            contactTable.getSelectionModel().clearSelection();
        } else {
            resultMessage.setVisible(true);
            resultMessage.setText("Contact with name '" + name + "' is founded.");
            for (int i = 0; i <contactService.getBookSize(); i++) {
                if (columnName.getCellData(i).equals(name)) {
                    contactTable.getSelectionModel().select(i);
                };
            }
        }
    }

    @FXML
    public void deleteContact(){
        textName.setText("Insert name of contact to be deleted");
        textName.setVisible(true);
        inputName.clear();
        inputName.setVisible(true);

        textAge.setVisible(false);
        inputAge.setVisible(false);
        textPhoneNumber.setVisible(false);
        inputPhoneNumber.setVisible(false);

        deleteButton.setVisible(true);
        contactTable.getSelectionModel().clearSelection();
        searchAndEditButton.setVisible(false);
        searchButton.setVisible(false);
        editButton.setVisible(false);
        resultMessage.setVisible(false);
    }

    @FXML
    public void delete(){
        String name = inputName.getText();
        boolean deleteResult = contactService.deleteContact(name);
        if (!deleteResult) {
            resultMessage.setVisible(true);
            resultMessage.setText("There is no contact with name '" + name + "' in address book.");
        } else {
            if (contactService.getBookSize() == 0) {
                textMessage.setText("Contact with name '" + name + "' was removed. Currenty no " +
                        "contacts in adress book, only \"Create contact\" option is available.");
                textMessage.setVisible(true);

                inputName.setVisible(false);
                textName.setVisible(false);

                searchContactButton.setDisable(true);
                editContactButton.setDisable(true);
                deleteContactButton.setDisable(true);
                deleteButton.setVisible(false);
                resultMessage.setVisible(false);
            } else {
                resultMessage.setText("Contact with name '" + name + "' was removed.");
            }
        }
    }

    @FXML
    public void editContact(){
        textName.setText("Insert name for search and editing");
        textName.setVisible(true);
        inputName.clear();
        inputName.setVisible(true);

        searchAndEditButton.setVisible(true);
        searchButton.setVisible(false);
        deleteButton.setVisible(false);
        resultMessage.setVisible(false);

        contactTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void SearchAndEditButton(){
        String name = inputName.getText();
        Contact searchResult = contactService.searchContact(name);
        if (searchResult == null) {
            resultMessage.setVisible(true);
            resultMessage.setText("There is no contact with name '" + name + "' in address book.");
            contactTable.getSelectionModel().clearSelection();
        } else {
            for (int i = 0; i <contactService.getBookSize(); i++) {
                if (columnName.getCellData(i).equals(name)) {
                    contactTable.getSelectionModel().select(i);
                };
            }
            tempContact = searchResult;

            textName.setVisible(true);
            textName.setText("UPDATE name");
            inputName.setVisible(true);
            inputName.setText(searchResult.getName());

            textAge.setVisible(true);
            textAge.setText("UPDATE age");
            inputAge.setVisible(true);
            inputAge.setText(Integer.toString(searchResult.getAge()));

            textPhoneNumber.setVisible(true);
            textPhoneNumber.setText("UPDATE phone number");
            inputPhoneNumber.setVisible(true);
            inputPhoneNumber.setText(Long.toString(searchResult.getPhoneNumber()));

            searchAndEditButton.setVisible(false);
            editButton.setVisible(true);
        }
    }

    @FXML
    public void edit(){
        String newName = inputName.getText();
        tempContact = contactService.editContact(tempContact, newName);
        String newAge = inputAge.getText();
        contactService.editContact(tempContact, new Integer(newAge));
        String newPhoneNumber = inputPhoneNumber.getText();
        contactService.editContact(tempContact, new Long(newPhoneNumber));
        contactTable.refresh();
    }

    @FXML
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        contactTable.setItems(contactService.showContactsFX());
    }

}

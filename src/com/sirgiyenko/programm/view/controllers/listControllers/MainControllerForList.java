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
    private TextArea resultMessage;

    @FXML
    public void createContact(){
        textMessage.setVisible(false);

        textName.setVisible(true);
        textName.setText("Insert name");
        inputName.setVisible(true);
        inputName.clear();

        textAge.setVisible(true);
        inputAge.setVisible(true);
        inputAge.clear();

        textPhoneNumber.setVisible(true);
        inputPhoneNumber.setVisible(true);
        inputPhoneNumber.clear();

        createButton.setVisible(true);
        searchButton.setVisible(false);
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

        resultMessage.setVisible(false);
    }

    @FXML
    public void searchContact(){
        textName.setText("Insert name for search");
        textName.setVisible(true);
        inputName.clear();
        inputName.setVisible(true);

        searchButton.setVisible(true);
        deleteButton.setVisible(false);
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

        deleteButton.setVisible(true);
        contactTable.getSelectionModel().clearSelection();
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
            initialize();
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
    public void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        contactTable.setItems(contactService.showContactsFX());
    }

}

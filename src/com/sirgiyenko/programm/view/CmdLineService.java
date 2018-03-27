package com.sirgiyenko.programm.view;

public interface CmdLineService {

    /*Method shows menu points for choose of necessary option as:
    * 1. create contact
    * 2. modify contact
    * 3. remove contact
    * 4. search contact by name and printing of respective phone number
    * 5. stop of application
    * etc.*/
    void showMenu();

    /*Method makes check for input value - string received from cmd (inputted by user).
    * The second input value shows what parameter is checked (menu, name, phone number, etc.).
    * Return true in case of correct value (int for menu items, string for name, int for phoneNumber, etc.).
    * In other case return false.*/
    boolean checkCmdValue(String s, String TYPEOFCHECK);

    /*Method shows request for name input from cmd. */
    void showRequestName();

    /*Method shows request for phone number input from cmd. */
    void showRequestPhoneNumber();

    /*Method manages next action depends of previously received value.
     * Case 1: contact creation - propose to input values for contact creation.
     * Case 2: contact modification - propose to choose parameter for modification.
     * Case 3: parameter for modification - propose to input old value.
     * Case 4: transferring of data to services.
     * Etc.
     * If all cmdParameters are available, creation of contactServiceImpl object cmdParameters.*/
    void manageView(int option);

    /*Method shows confirmation about successful action or mistake.*/
    void showConfirmation(boolean bool);

}

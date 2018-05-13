package com.sirgiyenko.programm;

import com.sirgiyenko.programm.dao.ContactDao;
import com.sirgiyenko.programm.dao.impl.DBContactDao;
import com.sirgiyenko.programm.dao.impl.FileSystemContactDao;
import com.sirgiyenko.programm.services.ContactService;
import com.sirgiyenko.programm.services.impl.ContactServiceImpl;
import com.sirgiyenko.programm.services.impl.FSContactServiceImpl;
import com.sirgiyenko.programm.view.CmdLineService;
import com.sirgiyenko.programm.view.impl.CmdLineServiceImpl;

import java.io.IOException;

/*Start of console application.*/

public class App {

    public static void main(String[] args) throws IOException {

        /*Creation of all layers of services which will be used in application
        (from lowest to highest):
        - layer DAO which works with instruments of long-term data keeping,
        - layer with business logic,
        - view services.*/


        /*Variant 1 - launching of application with saving of data in ArrayList.*/
        ContactService contactService = new ContactServiceImpl();

//        /*Variant 2 - launching of application with saving of data in file*/
//        ContactDao contactDao = new FileSystemContactDao();

//        /*Variant 3 - launching of application with saving of data in database*/
//        ContactDao contactDao = new DBContactDao();
//
//        //Creation of layers of upper levels for variants #2-3.
//        ContactService contactService = new FSContactServiceImpl(contactDao);

        /*Creation and start of graphic interface and application (for all variants).*/
        CmdLineService cmd = new CmdLineServiceImpl(contactService);
        cmd.runMainMenu();

    }

}

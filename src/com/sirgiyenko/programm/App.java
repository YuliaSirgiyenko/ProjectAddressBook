package com.sirgiyenko.programm;

import com.sirgiyenko.programm.services.impl.ContactServiceImpl;
import com.sirgiyenko.programm.view.CmdLineService;
import com.sirgiyenko.programm.view.impl.CmdLineServiceImpl;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        CmdLineService cmd = new CmdLineServiceImpl(new ContactServiceImpl());
        cmd.runMenu();

    }

}

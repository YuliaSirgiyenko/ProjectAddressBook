package com.sirgiyenko.programm;

import com.sirgiyenko.programm.view.CmdLineService;
import com.sirgiyenko.programm.view.impl.CmdLineServiceImpl;

public class App {

    public static void main(String[] args) {

        CmdLineService cmd = new CmdLineServiceImpl();
        cmd.showMenu();

    }

}

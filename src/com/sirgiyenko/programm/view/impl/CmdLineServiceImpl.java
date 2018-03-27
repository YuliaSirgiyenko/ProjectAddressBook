package com.sirgiyenko.programm.view.impl;

import com.sirgiyenko.programm.view.CmdLineService;

public class CmdLineServiceImpl implements CmdLineService{

    String nameCmd;
    int phoneNumberCmd;

    @Override
    public void showMenu() {
        System.out.println("Menu");
    }

    @Override
    public boolean checkCmdValue(String s, String TYPEOFCHECK) {
        return false;
    }

    @Override
    public void showRequestName() {

    }

    @Override
    public void showRequestPhoneNumber() {

    }

    @Override
    public void manageView(int option) {

    }

    @Override
    public void showConfirmation(boolean bool) {

    }


}

package com.sirgiyenko.programm.view;

import java.io.IOException;

public interface CmdLineService {

    /*Menu running: method should provide for printing of menu points. Depends on choosing of menu
    * item, definite method will be run.*/
    void runMainMenu() throws IOException;

}

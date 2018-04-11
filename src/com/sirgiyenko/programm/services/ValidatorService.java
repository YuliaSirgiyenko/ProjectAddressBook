package com.sirgiyenko.programm.services;

import java.io.IOException;

public interface ValidatorService {

    /*Method for check of console value, if it's int or not.*/
    public int readAge() throws IOException;

    /*Method for check of console value, if it's int or not.*/
    public long readPhoneNumber() throws IOException;

}

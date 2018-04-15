package com.sirgiyenko.programm.util;

import com.sirgiyenko.programm.businessException.IncorrectValueException;
import java.io.IOException;

public class ValidatorUtilImpl {

    private ValidatorUtilImpl(){
    }

    public static int checkAge(String age) throws IOException, IncorrectValueException {
        int ageInteger = Integer.parseInt(age);
        if (ageInteger <= 0 || ageInteger >= 90) {
            throw new IncorrectValueException();
        }
        return ageInteger;
    }

    public static long checkPhoneNumber(String phoneNumber) throws IOException, IncorrectValueException {
        long phoneNumberLong = Long.parseLong(phoneNumber);
        if (phoneNumberLong < 100) {
            throw new IncorrectValueException();
        }
        return phoneNumberLong;
    }

}

package com.sirgiyenko.programm.util;

import com.sirgiyenko.programm.businessException.IncorrectValueException;
import java.io.IOException;

public class ValidatorUtilImpl {

    private ValidatorUtilImpl(){
    }

    public static boolean notNullStringLine(String line) {
        boolean result = false;
        String temp = line.trim();
        if (temp.isEmpty()) {
            result = true;
        }
        return result;
    }

    public static int checkAge(String age) throws IncorrectValueException {
        int ageInteger = Integer.parseInt(age);
        if (ageInteger <= 0 || ageInteger >= 90) {
            throw new IncorrectValueException();
        }
        return ageInteger;
    }

    public static long checkPhoneNumber(String phoneNumber) throws IncorrectValueException {
        long phoneNumberLong = Long.parseLong(phoneNumber);
        if (phoneNumberLong < 100) {
            throw new IncorrectValueException();
        }
        return phoneNumberLong;
    }

}

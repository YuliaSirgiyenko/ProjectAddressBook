package com.sirgiyenko.programm.services.impl;

import com.sirgiyenko.programm.services.IncorrectValueException;
import com.sirgiyenko.programm.services.ValidatorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ValidatorServiceImpl implements ValidatorService {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int readAge() throws IOException {

        System.out.println("Pls., enter age");
        int age = 0;

        boolean ageFormat = false;
        while (!ageFormat) {
            try {
                age = Integer.parseInt(br.readLine());
                ageCheck(age);
                ageFormat = true;
            } catch (NumberFormatException | IncorrectValueException e) {
                System.out.println("Pls., enter correct age (between 1 and 110 years)");
            }
        }

    return age;
    }

    private boolean ageCheck(int age) throws IncorrectValueException {
        if (age <= 0 || age >= 110) {
            throw new IncorrectValueException();
        }
        return true;
    }

    @Override
    public long readPhoneNumber() throws IOException {

        System.out.println("Pls., enter phone number");
        long phoneNumber = 0;

        boolean phoneFormat = false;
        while (!phoneFormat) {
            try {
                phoneNumber = Long.parseLong(br.readLine());
                phoneNumberCheck(phoneNumber);
                phoneFormat = true;
            } catch (NumberFormatException | IncorrectValueException e) {
                System.out.println("Pls., enter correct phone number (with 9 digits as minimum)");
            }
        }

        return phoneNumber;
    }

    private boolean phoneNumberCheck(long phoneNumber) throws IncorrectValueException {
        if (phoneNumber < 100000000) {
            throw new IncorrectValueException();
        }
        return true;
    }

}

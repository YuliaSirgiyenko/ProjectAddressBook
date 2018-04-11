package com.sirgiyenko.programm.model;

public class Contact {

    private String name;
    private int age;
    private long phoneNumber;

    public Contact(String name, int age, long phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Contact{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone number='" + phoneNumber + '\'' +
                '}';
    }

}

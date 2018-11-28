package com.github.anthogis.tddproject;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;

     public Person() {

     }

    public Person(String firstName, String lastName, Date birthDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) throws RuntimeException {
        if (birthDate.compareTo(new Date()) > 0) {
            throw new RuntimeException("Person not born yet");
        } else {
            this.birthDate = birthDate;
        }
    }
}

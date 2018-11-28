package com.github.anthogis.tddproject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

     public Person() {

     }

    public Person(String firstName, String lastName, LocalDate birthDate) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws PersonNotBornYetException {
        if (birthDate.compareTo(LocalDate.now()) > 0) {
            throw new PersonNotBornYetException("Person not born yet");
        } else {
            this.birthDate = birthDate;
        }
    }

    public boolean isUnderAge() {
         return ChronoUnit.YEARS.between(birthDate, LocalDate.now()) >= 18;
    }


    public class PersonNotBornYetException extends RuntimeException{
        public PersonNotBornYetException(String message) {
            super(message);
        }
    }
}

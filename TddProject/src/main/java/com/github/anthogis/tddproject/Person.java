package com.github.anthogis.tddproject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Person() {
         firstName = null;
         lastName = null;
         birthDate = null;
     }

    public Person(String firstName, String lastName, LocalDate birthDate) 
            throws IllegalArgumentException {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws IllegalArgumentException {
        setNameHelper(firstName, true);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        setNameHelper(lastName, false);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws IllegalArgumentException {
        if (birthDate == null || birthDate.compareTo(LocalDate.now()) > 0) {
            throw new IllegalArgumentException("Given date is in the future");
        } else {
            this.birthDate = birthDate;
        }
    }

    private void setNameHelper(String name, boolean first) throws IllegalArgumentException{
        String exceptionMessage = "Argument firstName must have at least two characters";
        try {
            if (name.matches("[a-öA-Ö]{2,}")) {
                name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                if (first) {
                    this.firstName = name;
                } else {
                    this.lastName = name;
                }
            } else {
                throw new IllegalArgumentException(exceptionMessage);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public boolean isUnderAge() {
         return ChronoUnit.YEARS.between(birthDate, LocalDate.now()) >= 18;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return this.firstName.equals(((Person) obj).getFirstName()) &&
                    this.lastName.equals(((Person) obj).getLastName()) &&
                    this.birthDate.equals(((Person) obj).getBirthDate());
            
        } else {
            return false;
        }
    }
}

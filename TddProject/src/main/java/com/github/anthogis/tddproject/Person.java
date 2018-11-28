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
        if (firstName.equals(null) || firstName.length() < 2) {
            throw new IllegalArgumentException("Argument firstName must have at least two characters");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName.equals(null) || lastName.length() < 2) {
            throw new IllegalArgumentException("Argument firstName must have at least two characters");
        } else {
            this.lastName = lastName;
        }
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) throws IllegalArgumentException {
        if (birthDate.compareTo(LocalDate.now()) > 0) {
            throw new IllegalArgumentException("Given date is in the future");
        } else {
            this.birthDate = birthDate;
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

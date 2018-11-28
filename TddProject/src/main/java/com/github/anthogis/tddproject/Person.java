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
            throws PersonNotBornYetException, GetANewNameException {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws GetANewNameException {
        if (firstName.equals(null) || firstName.length() < 2) {
            throw new GetANewNameException("Get a new name, you freak");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws GetANewNameException {
        if (lastName.equals(null) || lastName.length() < 2) {
            throw new GetANewNameException("Get a new name, you freak");            
        } else {
            this.lastName = lastName;
        }
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
    
    public class GetANewNameException extends RuntimeException {
         public GetANewNameException(String message) {
             super(message);
         }
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

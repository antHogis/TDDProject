package com.github.anthogis.tddproject;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int MAX_STUDENTS;
    private List<Person> students;

    public Course(int MAX_STUDENTS) throws IllegalArgumentException{
        setMAX_STUDENTS(MAX_STUDENTS);
        students = new ArrayList<>(MAX_STUDENTS);
    }

    public boolean addStudent(Person student) {
        //If capacity not full, add Person and return true if successful
        return students.size() < MAX_STUDENTS ? students.add(student) : false;
    }

    public void setMAX_STUDENTS(int MAX_STUDENTS) throws IllegalArgumentException {
        if (MAX_STUDENTS > 0) {
            this.MAX_STUDENTS = MAX_STUDENTS;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

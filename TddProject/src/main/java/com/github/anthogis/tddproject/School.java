package com.github.anthogis.tddproject;

import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Person> students;
    private List<Course> courses;
    private int grantMoney;
    private static int courseCost = 20;

    public School() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        grantMoney = 0;
    }

    public void addGrant(int amount) throws IllegalArgumentException {
        if (amount >= 0) {
            grantMoney += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getGrantMoney() {
        return grantMoney;
    }

    public void addStudent(Person student) {
        students.add(student);
        addGrant(10);
    }

    public void addCourse(Course course) throws InsufficientFundsException,
            IllegalArgumentException {
        if (hasMoneyForNewCourse()) {
            courses.add(course);
            grantMoney -= courseCost;
        } else {
            throw new InsufficientFundsException();
        }
    }

    public void addStudentToCourse(Person student, Course course)
            throws IllegalArgumentException{
        addStudentToCourse(courses.indexOf(course), students.indexOf(student));
    }

    public void addStudentToCourse(int studentIndex, int courseIndex)
            throws IllegalArgumentException {
        try {
            if (!courses.get(courseIndex).addStudent(students.get(studentIndex))) {
                throw new IllegalArgumentException("Student could not be added!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    public List<String> getStudentInfoList() {
        List<String> studentList = new ArrayList<>(students.size());
        for (int i = 0; i < students.size(); i++) {
            Person s = students.get(i);
            studentList.add(String.format("%d. %s, %s %s", i, s.getLastName(),
                    s.getFirstName(), s.getBirthDate()));
        }

        return studentList;
    }

    public List<String> getCourseInfoList() {
        List<String> courseList = new ArrayList<>(courses.size());
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            courseList.add(String.format("%d. %s, Now: %d, Max: %d",
                    i, c.getCourseName(), c.studentsInCourse(), c.getMAX_STUDENTS()));
        }

        return courseList;
    }

    public boolean hasMoneyForNewCourse() {
        return grantMoney - courseCost >= 0;
    }

    public int studentsAmount() {
        return students.size();
    }

    public int coursesAmount() {
        return courses.size();
    }

    public List<Course> getCoursesCopy() {
        List<Course> newList = new ArrayList<>(courses.size());

        for (Course course : courses) {
            newList.add(course.copyOf());
        }

        return newList;
    }

    public boolean constructorSucceeded() {
        return students.size() == 0 && courses.size() == 0 && grantMoney == 0;
    }
}

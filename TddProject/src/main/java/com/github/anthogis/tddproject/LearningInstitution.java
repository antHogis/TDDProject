package com.github.anthogis.tddproject;

import java.util.ArrayList;
import java.util.List;

public class LearningInstitution {
    private List<Person> students;
    private List<Course> courses;
    private int grantMoney;
    private static int courseCost = 20;

    public LearningInstitution() {
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

    public List<String> getStudentInfoList() {
        List<String> studentList = new ArrayList<>(students.size());
        for (int i = 0; i < students.size(); i++) {
            Person s = students.get(i);
            studentList.add(String.format("%d. %s, %s %s\n", i, s.getLastName(),
                    s.getFirstName(), s.getBirthDate()));
        }

        return studentList;
    }

    public boolean hasMoneyForNewCourse() {
        return grantMoney - courseCost >= 0;
    }

}

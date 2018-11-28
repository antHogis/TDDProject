package com.github.anthogis.tddproject;

import java.util.ArrayList;
import java.util.List;

public class LearningInstitution {
    private List<Person> students;
    private List<Course> courses;
    private int grantMoney;

    public LearningInstitution() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        grantMoney = 0;
    }

    public void addGrant(int amount) {
        grantMoney += amount;
    }

    public int getGrantMoney() {
        return grantMoney;
    }

}

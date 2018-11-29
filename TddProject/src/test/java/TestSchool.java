import com.github.anthogis.tddproject.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class TestSchool {
    static School school;
    static Person person;
    static Course course;

    @BeforeClass
    public static void init() {
        school = new School();
        person = new Person("Testy", "Testington", LocalDate.now());
        course = new Course(1, "Test Course");
    }

    @Test
    public void constructor() {
        assertTrue(new School().constructorSucceeded());
    }

    @Test
    public void addGrantWithValidValue() {
        int addAmount = 10;
        int initialAmount = school.getGrantMoney();
        school.addGrant(addAmount);
        assertTrue(school.getGrantMoney() == initialAmount + addAmount);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addGrantWithInvalidValue() {
        school.addGrant(-10);
    }

    @Test
    public void getGrantMoney() {
        init();
        school.addStudent(person);
        assertEquals(10, school.getGrantMoney());
    }

    @Test
    public void addStudent() {
        init();
        school.addStudent(person);
        assertEquals(1, school.studentsAmount());
        assertEquals(10, school.getGrantMoney());
    }

    @Test (expected = InsufficientFundsException.class)
    public void addCourseInsufficientFunds() {
        init();
        school.addCourse(course);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addCourseIllegalArgument() {
        init();
        school.addStudent(person);
        school.addStudent(person);
        school.addCourse(new Course(-1, "Test"));
    }

    @Test
    public void addCourseValid() {
        init();
        school.addStudent(person);
        school.addStudent(person);
        school.addCourse(course);
    }

    @Test
    public void addStudentToCourseValid() {
        init();
        Person other = new Person("Unit", "Testington", LocalDate.now());

        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(person, course);
        assertEquals(person, school.getCoursesCopy().get(0).getStudentsCopy().get(0));
        assertNotEquals(other, school.getCoursesCopy().get(0).getStudentsCopy().get(0));

        init();
        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(1,0);
        assertEquals(other, school.getCoursesCopy().get(0).getStudentsCopy().get(0));
        assertNotEquals(person, school.getCoursesCopy().get(0).getStudentsCopy().get(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addStudentToCourseReferenceInvalid() {
        init();
        Person other = new Person("Unit", "Testington", LocalDate.now());

        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(new Person("Test", "Test", LocalDate.now()),
                course);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addStudentToCourseFull() {
        init();
        Person other = new Person("Unit", "Testington", LocalDate.now());
        course = new Course(1, "Test Course");

        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(person, course);
        school.addStudentToCourse(other, course);
    }

    @Test
    public void addStudentToCourseSuccess() {
        init();
        Person other = new Person("Unit", "Testington", LocalDate.now());
        course = new Course(1, "Test Course");

        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(person, course);
    }

    @Test
    public void getStudentInfoList() {
        init();
        for (int i = 0; i < 5; i++) {
            school.addStudent(person);
        }

        List<String> studentInfoList = school.getStudentInfoList();

        for (int i = 0; i < studentInfoList.size(); i++) {
            assertEquals(String.format("%d. %s, %s %s", i, person.getLastName(),
                    person.getFirstName(), person.getBirthDate()),
                    studentInfoList.get(i));
        }
    }

    @Test
    public void getCourseInfoList() {
        init();
        for (int i = 0; i < 10; i++) {
            school.addStudent(person);
        }

        for (int i = 0; i < 5; i++) {
            school.addCourse(course);
        }

        List<String> courseInfoList = school.getCourseInfoList();

        for (int i = 0; i < courseInfoList.size(); i++) {
            assertEquals(String.format("%d. %s, Now: %d, Max: %d",
                    i, course.getCourseName(), course.studentsInCourse(), course.getMAX_STUDENTS()),
                    courseInfoList.get(i));
        }
    }
    
    @Test
    public void hasMoneyForNewCourse() {
        init();
        assertFalse(school.hasMoneyForNewCourse());
        school.addStudent(person);
        assertFalse(school.hasMoneyForNewCourse());
        school.addStudent(person);
        assertTrue(school.hasMoneyForNewCourse());
    }

    @Test
    public void studentsAmount() {
        init();
        assertEquals(0, school.studentsAmount());
        int amount = 100;
        for (int i = 0; i < amount; i++) {
            school.addStudent(person);
        }

        assertEquals(amount, school.studentsAmount());
    }

    @Test
    public void coursesAmount() {
        init();
        assertEquals(0, school.coursesAmount());

        int amount = 100;
        for (int i = 0; i < amount; i++) {
            school.addStudent(person);
        }

        for(int i = 0; i < (amount / 2); i++) {
            school.addCourse(course);
        }

        assertEquals(amount / 2, school.coursesAmount());
    }

    @Test
    public void getCoursesCopy() {

    }

    
}

import com.github.anthogis.tddproject.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

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
    public void testConstructor() {
        assertTrue(new School().constructorSucceeded());
    }

    @Test
    public void testAddGrantWithValidValue() {
        int addAmount = 10;
        int initialAmount = school.getGrantMoney();
        school.addGrant(addAmount);
        assertTrue(school.getGrantMoney() == initialAmount + addAmount);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddGrantWithInvalidValue() {
        school.addGrant(-10);
    }

    @Test
    public void testAddStudent() {
        init();
        school.addStudent(person);
        assertEquals(1, school.studentsAmount());
        assertEquals(10, school.getGrantMoney());
    }

    @Test (expected = InsufficientFundsException.class)
    public void testAddCourseInsufficientFunds() {
        init();
        school.addCourse(course);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddCourseIllegalArgument() {
        init();
        school.addStudent(person);
        school.addStudent(person);
        school.addCourse(new Course(-1, "Test"));
    }

    @Test
    public void testAddCourseValid() {
        init();
        school.addStudent(person);
        school.addStudent(person);
        school.addCourse(course);
    }

    @Test
    public void testAddStudentToCourseValid() {
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
    public void testAddStudentToCourseReferenceInvalid() {
        init();
        Person other = new Person("Unit", "Testington", LocalDate.now());

        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(new Person("Test", "Test", LocalDate.now()),
                course);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddStudentToCourseFull() {
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
    public void testAddStudentToCourseSuccess() {
        init();
        Person other = new Person("Unit", "Testington", LocalDate.now());
        course = new Course(1, "Test Course");

        school.addStudent(person);
        school.addStudent(other);
        school.addCourse(course);
        school.addStudentToCourse(person, course);
    }

    @Test
    public void getGrantMoney() {

    }



    @Test
    public void getStudentInfoList() {
    }
}

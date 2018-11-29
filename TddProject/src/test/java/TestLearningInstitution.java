import com.github.anthogis.tddproject.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestLearningInstitution {
    static LearningInstitution school;
    static Person person;
    static Course course;

    @BeforeClass
    public static void init() {
        school = new LearningInstitution();
        person = new Person("Testy", "Testington", LocalDate.now());
        course = new Course(1, "Test Course");
    }

    @Test
    public void testConstructor() {
        assertTrue(new LearningInstitution().constructorSucceeded());
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
    public void getGrantMoney() {
    }



    @Test
    public void getStudentInfoList() {
    }
}

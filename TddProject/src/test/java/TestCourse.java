import com.github.anthogis.tddproject.Course;
import com.github.anthogis.tddproject.Person;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestCourse {
    static Course course;

    @Test
    public void constructor() {
        int MAX_STUDENTS = 2;
        String courseName = "Test";

        course = new Course(MAX_STUDENTS,courseName);
        assertEquals(MAX_STUDENTS, course.getMAX_STUDENTS());
        assertEquals(courseName, course.getCourseName());
    }

    @Test
    public void addStudent() {
        course = new Course(1, "Test");
        assertTrue(course.addStudent(new Person()));
        assertFalse(course.addStudent(new Person()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMaxStudentsNegative() {
        course = new Course(-3, "Test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMaxStudentsZero() {
        course = new Course(0, "Test");
    }

    @Test
    public void setMaxStudentsPositive() {
        course = new Course(1, "Test");
    }

    @Test
    public void studentsInCourse() {
        course = new Course(1, "Test");
        assertEquals(0, course.studentsInCourse());
        course.addStudent(new Person("Test", "Test",
                LocalDate.parse("1111-11-11")));
        assertEquals(1, course.studentsInCourse());
    }

    @Test
    public void getMAX_STUDENTS() {
        course = new Course(2, "Test");
        assertEquals(2, course.getMAX_STUDENTS());
    }

    @Test
    public void getCourseName() {
        course = new Course(2, "Test");
        assertEquals("Test", course.getCourseName());
    }


}

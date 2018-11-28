import com.github.anthogis.tddproject.Course;
import com.github.anthogis.tddproject.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCourse {
    static Course course;


    @Test
    public void testAdd() {
        course = new Course(1);
        course.addStudent(new Person());
        assertFalse(course.addStudent(new Person()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxStudentsNegative() {
        course = new Course(-3);
    }
}

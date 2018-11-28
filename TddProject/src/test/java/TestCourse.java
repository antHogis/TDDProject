import com.github.anthogis.tddproject.Course;
import com.github.anthogis.tddproject.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCourse {

    @Test
    public void testAdd() {
        Course course = new Course(1);
        course.addStudent(new Person());
        assertFalse(course.addStudent(new Person()));
    }
}

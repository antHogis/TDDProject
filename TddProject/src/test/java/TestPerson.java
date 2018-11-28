import com.github.anthogis.tddproject.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TestPerson {
    static Person person;
    static LocalDate date;

    @BeforeClass
    public static void init() {
        person = new Person();
        date = LocalDate.now();
    }

    @Test(expected = Person.PersonNotBornYetException.class)
    public void testSetBirthDateException() {
        init();
        date = date.plusMonths(1);
        person.setBirthDate(date);
    }

    @Test
    public void testIsUnderAge() {
        init();
        date = date.minusYears(20);
        person.setBirthDate(date);
        assertTrue(person.isUnderAge());

        init();
        date = date.minusYears(17);
        person.setBirthDate(date);
        assertFalse(person.isUnderAge());
    }


}

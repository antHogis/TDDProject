import com.github.anthogis.tddproject.Person;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class TestPerson {
    static Person person;
    static Calendar calendar;

    @BeforeClass
    public static void init() {
        person = new Person();
        calendar = new GregorianCalendar();
    }

    @Test(expected = Person.PersonNotBornYetException.class)
    public void testSetBirthDateException() {
        init();
        calendar.roll(Calendar.DAY_OF_MONTH, 1);
        person.setBirthDate(new Date(calendar.getTimeInMillis()));
    }

    @Test
    public void testIsUnder18() {
        init();

    }


}

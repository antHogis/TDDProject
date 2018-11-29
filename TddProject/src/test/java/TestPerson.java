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

    @Test
    public void testConstructorValid() {
        person = new Person("Test", "Test", LocalDate.parse("1990-01-01"));
    }

    @Test
    public void testConstructorInvalid() {
        boolean caught = false;

        try {
            person = new Person(null, "Test", LocalDate.parse("1990-01-01"));
        } catch (IllegalArgumentException e) {
            caught = true;
        } finally {
            assertTrue(caught);
        }

        caught = false;

        try {
            person = new Person("Test", null, LocalDate.parse("1990-01-01"));
        } catch (IllegalArgumentException e) {
            caught = true;
        } finally {
            assertTrue(caught);
        }

        caught = false;

        try {
            person = new Person("Test", "test", null);
        } catch (IllegalArgumentException e) {
            caught = true;
        } finally {
            assertTrue(caught);
        }
    }

    @Test(expected = IllegalArgumentException.class)
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

    private void setNameHelper(String[] names, boolean assertTrue, boolean setFirst) {
        boolean[] results = new boolean[names.length];

        for (int i = 0; i < names.length; i++) {
            try {
                if (setFirst) {
                    person.setFirstName(names[i]);
                } else {
                    person.setLastName(names[i]);
                }
                results[i] = true;
            } catch (IllegalArgumentException e) {
                results[i] = false;
            }
        }

        for (boolean result : results) {
            if (assertTrue) assertTrue(result);
            else assertFalse(result);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetFirstNameNull() {
        init();
        person.setFirstName(null);
    }

    @Test
    public void testSetFirstNameInvalid() {
        init();
        person.setFirstName("Valid");
        String[] invalidNames = {"a", "...", "?boy", "girl=?"};
        setNameHelper(invalidNames, false, true);
        assertEquals(person.getFirstName(), "Valid");
    }

    @Test
    public void testSetFirstNameValid() {
        init();
        String[] validNames = {"Po", "Yrjö", "George"};
        setNameHelper(validNames, true, true);
        assertEquals(person.getFirstName(), validNames[validNames.length - 1]);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetLastNameNull() {
        init();
        person.setLastName(null);
    }

    @Test
    public void testSetLastNameInvalid() {
        init();
        person.setLastName("Valid");
        String[] invalidNames = {"a", "...", "?boy", "girl=?"};
        setNameHelper(invalidNames, false, false);
        assertEquals("Valid", person.getLastName());
    }

    @Test
    public void testSetLastNameValid() {
        init();
        String[] validNames = {"Po", "Yrjö", "george"};
        setNameHelper(validNames, true, false);
        String lastofValid = validNames[validNames.length - 1].substring(0,1).toUpperCase() +
                validNames[validNames.length - 1].substring(1).toLowerCase();
        assertEquals(lastofValid, person.getLastName());
    }


}

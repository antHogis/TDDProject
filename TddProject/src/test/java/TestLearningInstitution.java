import com.github.anthogis.tddproject.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLearningInstitution {
    static LearningInstitution school;

    @BeforeClass
    public static void init() {
        school = new LearningInstitution();
    }

    @Test
    public void testAddGrant() {
        int addAmount = 10;
        int initialAmount = school.getGrantMoney();
        school.addGrant(addAmount);
        assertTrue(school.getGrantMoney() == initialAmount + addAmount);
    }
}

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestPerson.class,
    TestCourse.class,
    TestSchool.class
})
public class TddProjectSuite {
    //This class only functions as a holder for annotations above.
}

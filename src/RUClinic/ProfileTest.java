package RUClinic;
import org.junit.Before;
import org.junit.Test;

/**
 * This Junit class is used to test the compareTo() method for
 * the Profile class to ensure that it works
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class ProfileTest {
    Date dob1 = new Date("12/13/1989");
    Date dob2 = new Date("5/20/1990");
    Date dob3 = new Date("5/20/1991");
    private final int LESSTHAN = -1;
    private final int GREATERTHAN = 1;
    private final int EQUALS = 0;

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testLastName() {
        Profile profile1 = new Profile("John", "Anderson", dob1);
        Profile profile2 = new Profile("John", "Brown", dob1);
        Profile profile7 = new Profile("John", "Smith", dob1);
        Profile profile8 = new Profile("John", "Doe", dob1);
        assert(profile1.compareTo(profile2) == LESSTHAN); // Anderson < Brown
        assert(profile7.compareTo(profile8) == GREATERTHAN); // Smith > Doe
    }

    @Test
    public void testFirstName() {
        Profile profile3 = new Profile("Alice", "Doe", dob1);
        Profile profile4 = new Profile("Bob", "Doe", dob1);
        Profile profile9 = new Profile("Charlie", "Doe", dob1);
        Profile profile10 = new Profile("Bob", "Doe", dob1);
        assert(profile3.compareTo(profile4) == LESSTHAN); // Alice < Bob
        assert(profile9.compareTo(profile10) == GREATERTHAN); // Charlie > Bob
    }

    @Test
    public void testDOB() {
        Profile profile5 = new Profile("John", "Doe", dob1);
        Profile profile6 = new Profile("John", "Doe", dob2);
        Profile profile11 = new Profile("John", "Doe", dob3);
        Profile profile12 = new Profile("John", "Doe", dob1);
        assert(profile5.compareTo(profile6) == LESSTHAN); // 12/13/1989 < 5/20/1990
        assert(profile11.compareTo(profile12) == GREATERTHAN); // 5/20/1991 > 12/13/1989
    }

    @Test
    public void testEquals() {
        Profile profile13 = new Profile("John", "Doe", dob1);
        Profile profile14 = new Profile("John", "Doe", dob1);
        assert(profile13.compareTo(profile14) == EQUALS); // same fname, lname, dob
    }
}

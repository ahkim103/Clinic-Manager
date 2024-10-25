package RUClinic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This Junit class is used to test the isValid() method for
 * the Date class to ensure that it works
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class DateTest {
    @Before
    public void setUp() throws Exception {}

    @Test
    public void testInvalidDateToday() {
        Date currentDate = new Date(); // invalid test date (today)
        assertFalse(currentDate.isValid());
    }

    @Test
    public void testInvalidDatePast() {
        Date testDate2 = new Date("9/16/2024"); // invalid test date (before today)
        assertFalse(testDate2.isValid());
    }

    @Test
    public void testInvalidWeekend() {
        Date testDate3 = new Date("2/15/2025"); // invalid test date (weekend)
        assertFalse(testDate3.isValid());
    }

    @Test
    public void testInvalid6Months() {
        Date testDate4 = new Date("5/10/2026"); // invalid test date (more than 6 months away)
        assertFalse(testDate4.isValid());
    }

    @Test
    public void testValid() {
        Date testDate5 = new Date("10/31/2024"); // valid test case
        Date testDate6 = new Date("11/28/2024"); // valid test case
        assertTrue(testDate5.isValid());
        assertTrue(testDate6.isValid());
    }
}

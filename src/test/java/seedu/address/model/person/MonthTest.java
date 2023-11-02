package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class MonthTest {
    public static final String MONTH_WITH_CHARACTERS = "1a";
    public static final String MONTH_WITH_DECIMALS = "1.00";
    public static final String ZERO_MONTH = "0";
    public static final String NEGATIVE_MONTH = "-1";
    public static final String INVALID_MONTH = "13";
    public static final String VALID_MONTH = "1";

    @Test
    public void execute_monthEqualsSuccess() {
        Month testMonth = new Month(1);
        assertEquals(new Month(Integer.parseInt(VALID_MONTH)), testMonth);
        assertEquals(testMonth, testMonth);
        assertNotEquals(testMonth, null);
    }

    @Test
    public void execute_monthFailure() {
        assertThrows(Exception.class, () -> new Month(0));
        assertThrows(Exception.class, () -> new Month(13));
        assertThrows(Exception.class, () -> new Month(100000));
    }

    @Test
    public void execute_containsAlphabetsSuccess() {
        assertTrue(Month.containsAlphabetsOrDecimals(MONTH_WITH_CHARACTERS));
    }

    @Test
    public void execute_containsDecimalsSuccess() {
        assertTrue(Month.containsAlphabetsOrDecimals(MONTH_WITH_DECIMALS));
    }

    @Test
    public void execute_doesNotContainsAlphabetsOrDecimalsSuccess() {
        assertFalse(Month.containsAlphabetsOrDecimals(VALID_MONTH));
    }

    @Test
    public void execute_containsZeroSuccess() {
        assertTrue(Month.isZeroMonth(ZERO_MONTH));
    }

    @Test
    public void execute_doesNotContainsZeroSuccess() {
        assertFalse(Month.isZeroMonth(VALID_MONTH));
    }

    @Test
    public void execute_containsNegativeSuccess() {
        assertTrue(Month.isNegativeMonth(NEGATIVE_MONTH));
    }

    @Test
    public void execute_doesNotContainsNegativeSuccess() {
        assertFalse(Month.isNegativeMonth(VALID_MONTH));
    }

    @Test
    public void execute_invalidMonth() {
        assertFalse(Month.isValidMonth(INVALID_MONTH));
        assertFalse(Month.isValidMonth(NEGATIVE_MONTH));
    }

    @Test
    public void execute_validMonth() {
        assertTrue(Month.isValidMonth(VALID_MONTH));
    }
}

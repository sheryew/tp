package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class HasLeaveThisMonthPredicateTest {

    @Test
    void test_hasLeaveThisMonth_returnsTrue() {
        HasLeaveThisMonthPredicate predicate1 = new HasLeaveThisMonthPredicate("1");
        assertTrue(predicate1.test(new PersonBuilder().withLeave("111001000011").build()));
    }

    @Test
    void test_hasLeaveThisMonth_returnsFalse() {
        HasLeaveThisMonthPredicate predicate1 = new HasLeaveThisMonthPredicate("4");
        assertFalse(predicate1.test(new PersonBuilder().withLeave("111001000011").build()));
    }

    @Test
    void testEquals() {
        HasLeaveThisMonthPredicate predicate1 = new HasLeaveThisMonthPredicate("1");
        HasLeaveThisMonthPredicate predicate2 = new HasLeaveThisMonthPredicate("1");
        HasLeaveThisMonthPredicate predicate3 = new HasLeaveThisMonthPredicate("12");
        assertEquals(predicate1, predicate1);
        assertEquals(predicate1, predicate2);
        assertNotEquals(predicate1, predicate3);
        assertNotEquals(predicate1, null);
        assertNotEquals(predicate1, 1);
    }
}

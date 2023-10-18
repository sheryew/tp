package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasLeaveAnyMonthPredicateTest {

    @Test
    void test_hasLeaveAnyMonth_returnsTrue() {
        HasLeaveAnyMonthPredicate predicate1 = new HasLeaveAnyMonthPredicate();
        assertTrue(predicate1.test(new PersonBuilder().withLeave("111001000011").build()));
    }

    @Test
    void test_hasLeaveAnyMonth_returnsFalse() {
        HasLeaveAnyMonthPredicate predicate1 = new HasLeaveAnyMonthPredicate();
        assertFalse(predicate1.test(new PersonBuilder().withLeave("000000000000").build()));
    }

    @Test
    void testEquals() {
        HasLeaveAnyMonthPredicate predicate1 = new HasLeaveAnyMonthPredicate();
        HasLeaveAnyMonthPredicate predicate2 = new HasLeaveAnyMonthPredicate();

        assertEquals(predicate1, predicate1);
        assertEquals(predicate1, predicate2);
        assertNotEquals(predicate1, null);
        assertNotEquals(predicate1, 1);
    }
}
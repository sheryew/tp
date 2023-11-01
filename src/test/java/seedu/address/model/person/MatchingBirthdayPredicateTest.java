package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MatchingBirthdayPredicateTest {
    private Person subject;

    @BeforeEach
    public void setup() {
        subject = new PersonBuilder().build();
    }

    @Test
    public void predicate_success() {
        MatchingBirthdayPredicate predicate1 = new MatchingBirthdayPredicate(new Month(1));
        assertTrue(predicate1.test(subject));
    }

    @Test
    public void predicate_failure() {
        MatchingBirthdayPredicate predicate2 = new MatchingBirthdayPredicate(new Month(2));
        assertFalse(predicate2.test(subject));
    }
}

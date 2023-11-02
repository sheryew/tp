package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MatchingBirthdayPredicateTest {
    private static Person subject;

    @BeforeAll
    static void setup() {
        subject = new PersonBuilder().build();
    }

    @Test
    public void predicate_success() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(1));
        MatchingBirthdayPredicate predicate1 = new MatchingBirthdayPredicate(monthList);
        assertTrue(predicate1.test(subject));
    }

    @Test
    public void predicate_failure() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(2));
        MatchingBirthdayPredicate predicate2 = new MatchingBirthdayPredicate(monthList);
        assertFalse(predicate2.test(subject));
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @Test
    public void equalsItself_success() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(2));
        MatchingBirthdayPredicate predicate1 = new MatchingBirthdayPredicate(monthList);
        assertEquals(predicate1, predicate1);
    }

    @Test
    public void equalsList_success() {
        List<Month> monthList1 = new ArrayList<>();
        monthList1.add(new Month(2));
        monthList1.add(new Month(3));
        MatchingBirthdayPredicate predicate1 = new MatchingBirthdayPredicate(monthList1);

        List<Month> monthList2 = new ArrayList<>();
        monthList2.add(new Month(2));
        monthList2.add(new Month(3));
        MatchingBirthdayPredicate predicate2 = new MatchingBirthdayPredicate(monthList2);
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void equalList_failure() {
        List<Month> monthList1 = new ArrayList<>();
        monthList1.add(new Month(2));
        monthList1.add(new Month(3));
        MatchingBirthdayPredicate predicate1 = new MatchingBirthdayPredicate(monthList1);

        List<Month> monthList2 = new ArrayList<>();
        monthList2.add(new Month(2));
        monthList2.add(new Month(4));
        MatchingBirthdayPredicate predicate2 = new MatchingBirthdayPredicate(monthList2);
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void stringRep_success() {
        List<Month> monthList1 = new ArrayList<>();
        monthList1.add(new Month(2));
        monthList1.add(new Month(3));
        MatchingBirthdayPredicate predicate1 = new MatchingBirthdayPredicate(monthList1);

        assertEquals(predicate1.toString(), "Feb,Mar");
    }
}

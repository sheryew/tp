package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class MatchingDepartmentPredicateTest {

    @Test
    void test_matchingDepartment_returnsTrue() {
        Department department1 = new Department("HR");
        Department department2 = new Department("hr");
        MatchingDepartmentPredicate predicate1 = new MatchingDepartmentPredicate(department1);
        MatchingDepartmentPredicate predicate2 = new MatchingDepartmentPredicate(department2);
        assertTrue(predicate1.test(new PersonBuilder().withDepartment("HR").build()));
        assertTrue(predicate2.test(new PersonBuilder().withDepartment("HR").build()));
    }

    @Test
    void test_matchingDepartment_returnsFalse() {
        Department department1 = new Department("HR");
        MatchingDepartmentPredicate predicate1 = new MatchingDepartmentPredicate(department1);
        assertFalse(predicate1.test(new PersonBuilder().withDepartment("IT").build()));
    }
}

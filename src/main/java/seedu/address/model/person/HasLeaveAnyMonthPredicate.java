package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} has a leave in any month.
 */
public class HasLeaveAnyMonthPredicate implements Predicate<Person> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param person the input argument
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    @Override
    public boolean test(Person person) {
        return person.getLeave().leaveExistInAnyMonth();
    }
}

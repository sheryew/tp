package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} has a leave in {@code Month}.
 */
public class HasLeaveThisMonthPredicate implements Predicate<Person> {

    private final String month;

    public HasLeaveThisMonthPredicate(String month) {
        this.month = month;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param person the input argument
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    @Override
    public boolean test(Person person) {
        return person.getLeave().leaveExistInMonth(month);
    }
}

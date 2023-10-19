package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code dob} matches the given the month.
 */
public class MatchingBirthdayPredicate implements Predicate<Person> {
    private final Month month;

    public MatchingBirthdayPredicate(Month month) {
        this.month = month;
    }

    public Month getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchingBirthdayPredicate)) {
            return false;
        }

        MatchingBirthdayPredicate otherPredicate = (MatchingBirthdayPredicate) other;
        return month.equals(otherPredicate.getMonth());
    }

    @Override
    public boolean test(Person person) {
        return this.month.equals(person.getDob().getMonth());
    }
}

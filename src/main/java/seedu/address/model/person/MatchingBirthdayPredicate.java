package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code dob} matches the given the month.
 */
public class MatchingBirthdayPredicate implements Predicate<Person> {
    private final List<Month> month;

    public MatchingBirthdayPredicate(List<Month> month) {
        this.month = month;
    }

    public List<Month> getMonth() {
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
        List<Month> otherList = otherPredicate.getMonth();
        for (int i = 0; i < this.month.size(); i++) {
            if (!this.month.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean test(Person person) {
        return this.month.contains(person.getDob().getMonth());
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < month.size(); i++) {
            if (i == month.size() - 1) {
                message.append(month.get(i).getMonthName());
            } else {
                message.append(month.get(i).getMonthName()).append(",");
            }
        }
        return message.toString();
    }
}

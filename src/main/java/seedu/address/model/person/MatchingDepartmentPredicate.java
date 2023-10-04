package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Department} matches the department name given.
 */
public class MatchingDepartmentPredicate implements Predicate<Person> {

    private final Department department;

    public MatchingDepartmentPredicate(Department department) {
        this.department = department;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param person the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Person person) {
        return this.department.equals(person.getDepartment());
    }
}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's department.
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS = "Departments should not be blank";

    public final String department;

    /**
     * Constructs a {@code Department}.
     *
     * @param dep A valid department.
     */
    public Department(String dep) {
        requireNonNull(dep);
        dep = dep.trim();
        checkArgument(isValidDepartment(dep), MESSAGE_CONSTRAINTS);
        department = dep;
    }

    /**
     * Returns true if a given string is a valid department.
     */
    public static boolean isValidDepartment(String test) {
        return !test.trim().equals("");
    }

    @Override
    public String toString() {
        return department;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Department)) {
            return false;
        }

        Department otherDep = (Department) other;
        return department.equalsIgnoreCase(otherDep.department);
    }

    @Override
    public int hashCode() {
        return department.hashCode();
    }

}

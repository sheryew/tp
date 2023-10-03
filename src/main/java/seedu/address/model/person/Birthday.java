package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's dob.
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Invalid date of birth. Please provide date of birth with format: YYYY-MM-DD";

    public final String dob;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param dateStr A valid dob.
     */
    public Birthday(String dateStr) {
        requireNonNull(dateStr);
        dateStr = dateStr.trim();
        try {
            LocalDate.parse(dateStr);
            dob = dateStr;
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid dob.
     */
    public static boolean isValidDob(String test) {
        String dateStr = test.trim();
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return LocalDate.parse(dob).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherDate = (Birthday) other;
        return dob.equals(otherDate.dob);
    }

    @Override
    public int hashCode() {
        return dob.hashCode();
    }

}

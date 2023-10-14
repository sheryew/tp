package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Person's dob.
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Invalid date of birth. Please provide date of birth with format: YYYY-MM-DD.\n"
            + "An employee must be at least 13 years old.";

    public final String dob;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param dateStr A valid dob.
     */
    public Birthday(String dateStr) {
        requireNonNull(dateStr);
        dateStr = dateStr.trim();
        checkArgument(isValidDob(dateStr), MESSAGE_CONSTRAINTS);
        dob = dateStr;
    }

    /**
     * Returns true if a given string is a valid dob.
     */
    public static boolean isValidDob(String test) {
        String dateStr = test.trim();
        try {
            LocalDate birthday = LocalDate.parse(dateStr);
            LocalDate legalBirthday = LocalDate.now().minusYears(13);
            if (legalBirthday.until(birthday, ChronoUnit.DAYS) > 0) {
                return false;
            }
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

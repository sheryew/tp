package seedu.address.model.person;

/**
 * Represents the month of the dob of the person.
 */
public class Month {
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS_BLANK_MONTH =
            "Month provided cannot be blank.\n" + "Did you mean to type 'birthday' ?";
    public static final String MESSAGE_CONSTRAINTS_NEGATIVE_MONTH =
            "Month provided cannot be negative.";
    public static final String MESSAGE_CONSTRAINTS_MONTH_OVER =
            "Month provided cannot be greater than 12.";
    public static final String MESSAGE_CONSTRAINTS_ZERO_MONTH =
            "Month provided cannot be 0.";
    public static final String MESSAGE_CONSTRAINTS_MONTH_INVALID_CHARACTERS =
            "Month provided cannot be a decimal nor contain alphabets.";

    public final int month;

    /**
     * Constructs an {@code Month}
     * @param monthValue the integer representation of the intended month.
     */
    public Month(int monthValue) {
        month = monthValue;
    }

    /**
     * Checks if the given input contains characters or decimals.
     * @param monthString the string representation of the month number.
     * @return true if it contains char/decimals, false if clear.
     */
    public static boolean containsAlphabetsOrDecimals(String monthString) {
        String monthTrimmed = monthString.trim();
        try {
            Integer.parseInt(monthTrimmed);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     * Check if the given input is 0.
     * @param monthString the string representation of the month number.
     * @return true if the value is 0.
     */
    public static boolean isZeroMonth(String monthString) {
        String monthTrimmed = monthString.trim();
        int value = Integer.parseInt(monthTrimmed);
        return value == 0;
    }

    /**
     * Check if the given input is negative.
     * @param monthString the string representation of the month number.
     * @return true if the value is negative.
     */
    public static boolean isNegativeMonth(String monthString) {
        String monthTrimmed = monthString.trim();
        int value = Integer.parseInt(monthTrimmed);
        return value < 0;
    }

    /**
     * Check if the given input lies between 1 and 12 (inclusive).
     * @param monthString the string representation of the month number.
     * @return true if it is between 1 and 12.
     */
    public static boolean isValidMonth(String monthString) {
        String monthTrimmed = monthString.trim();
        int value = Integer.parseInt(monthTrimmed);
        return value < 13;
    }

    public int getMonthValue() {
        return month;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Month)) {
            return false;
        }

        Month otherDate = (Month) other;
        return month == otherDate.getMonthValue();
    }

}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's salary and claim budget.
 */
public class Money {

    public static final String MESSAGE_CONSTRAINTS = "Dollar amount should be a positive integer";

    public final String amount;

    /**
     * Constructs a {@code Money}.
     *
     * @param numStr A valid dollar amount.
     */
    public Money(String numStr) {
        requireNonNull(numStr);
        try {
            Integer num = Integer.valueOf(numStr);
            checkArgument(num >= 0, MESSAGE_CONSTRAINTS);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        amount = numStr;
    }

    /**
     * Returns true if a given string is a valid dollar amount.
     */
    public static boolean isValidMoney(String test) {
        try {
            Integer num = Integer.valueOf(test);
            checkArgument(num >= 0, MESSAGE_CONSTRAINTS);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder rev = new StringBuilder(amount).reverse();
        String money = "";
        for (int i = 0; i < rev.length(); i++) {
            if (i > 0 && i % 3 == 0) {
                money += ",";
            }
            money += rev.charAt(i);
        }
        money += "$";
        return new StringBuilder(money).reverse().toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Money)) {
            return false;
        }

        Money otherMoney = (Money) other;
        return amount.equals(otherMoney.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}

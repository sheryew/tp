package seedu.address.model.person;

import java.util.Objects;

public class Claim {
    public static final String NO_SYMBOLS_ERROR = "Kindly state whether the employee wants to deduct/add the claim amount! Provide either +/- in front of the amount!";
    public static final String ALPHABETS_ERROR = "Kindly only input integers for the claim amount!";

    public final boolean isSubtract;
    public final int amount;

    public Claim(String numStr) {
        char firstChar = numStr.charAt(0);
        if (firstChar == '+') {
            this.isSubtract = false;
        } else {
            this.isSubtract = true;
        }
        this.amount = Integer.parseInt(numStr.substring(1));
    }

    public static boolean comtainsSymbol(String args) {
        char firstChar = args.charAt(0);
        return firstChar == '+' || firstChar == '-';
    }

    public static boolean isCorrectAmountType(String args) {
        String amount = args.substring(1);
        return amount.matches("\\d+");
    }
}

package seedu.address.model.person;

/**
 * Represents a Person's Claim in the address book.
 * Ensures boolean variable and amount is provided.
 */
public class Claim {
    public static final String NO_SYMBOLS_ERROR = "Kindly state whether the employee wants to deduct/add "
            + "the claim amount! Provide either +/- in front of the amount!";
    public static final String ALPHABETS_ERROR = "Kindly only input integers for the claim amount!";

    public final boolean isSubtract;
    public final long amount;

    /**
     * Returns a Claim Object which contains of two variables, isSubtract and amount.
     * isSubtract variable represents whether this claim is either addition/subtraction of claim budget.
     * amount variable represents whether the claim amount employee is hoping to claim.
     *
     * @param numStr String Object of user's input.
     */
    public Claim(String numStr) {
        char firstChar = numStr.charAt(0);
        if (firstChar == '+') {
            this.isSubtract = false;
        } else {
            this.isSubtract = true;
        }
        this.amount = Long.parseLong(numStr.substring(1));
    }

    /**
     * Returns a boolean object where True if symbol (+/-) is provided before claim amount else False.
     *
     * @param args String Object that contains parsed out portions from user's CLI input.
     * @return Boolean where True represents correct claim input format else False.
     */
    public static boolean comtainsSymbol(String args) {
        char firstChar = args.charAt(0);
        return firstChar == '+' || firstChar == '-';
    }

    /**
     * Returns boolean object where True if claim amount only contains of digits else False.
     * This portion represents post symbol portion. For example, if args == "+500", we are obly care about "500".
     *
     * @param args String object that contains parsed out partions from user's CLI input.
     * @return Boolean where True represents claim amount was given in correct format else False.
     */
    public static boolean isCorrectAmountType(String args) {
        String amount = args.substring(1);
        return amount.matches("\\d+");
    }
}

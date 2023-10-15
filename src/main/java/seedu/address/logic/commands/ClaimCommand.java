package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Money;
import seedu.address.model.person.Person;

/**
 * Performs claims for each employee and alters respective claim budgets.
 */
public class ClaimCommand extends Command {

    public static final String COMMAND_WORD = "claim";
    public static final String MESSAGE_EMPTY = "Kindly state the index and amount that the employee hopes to process!\n" +
            "Parameters: INDEX (must be positive integer) $/CLAIM_AMOUNT.\n" +
                "Format: Positive CLAIM_AMOUNT indicates addition and negative indicates subtraction.\n" +
                    "Example: claim 1 $/+500";
    public static final String AMOUNT_EMPTY = "Kindly state the amount that the employee hopes to process!";
    public static final String CLAIM_SUCCESS = "Claim has been successfully processed!\n";

    private final Index index;
    private final Boolean isSubtract;
    private final long amount;

    /**
     *  Initialises a ClaimCommand Objects with three variables being index, isSubtract and amount.
     *
     * @param index Index Object representing the position of individual within the list of employees.
     * @param isSubtract Boolean Object where True represents deduction and False represents addition.
     * @param amount Long Object representing the claim amount user is submitting.
     */
    public ClaimCommand(Index index, Boolean isSubtract, long amount) {
        this.index = index;
        this.isSubtract = isSubtract;
        this.amount = amount;
    }

    /**
     * Returns CommandResult Object after successfully updating user's claim budget.
     * Updating the Person Object in the list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which highlights the new claim budget the individual has.
     * @throws CommandException Exception thrown if index input from HR is beyond the pre-existing max list index.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        long prevClaimBudget = Integer.parseInt(personToEdit.getClaimBudget().amount);
        Money claimBudget = calculateNewClaimBudget(prevClaimBudget);
        Person editedPerson = postClaimPerson(personToEdit, claimBudget);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format("%s Remaining claim %s has: %s",
                CLAIM_SUCCESS, editedPerson.getName(), claimBudget));
    }

    /**
     * Returns a Money Object which represents the new amount the user has after the claiming process is completed.
     *
     * @param prevClaimBudget long object on user's claim budget before the claim process.
     * @return Money Object that highlights the new claim budget the user has.
     * @throws CommandException Exception if the subtracted claim amount is more the user's claim budget.
     */
    public Money calculateNewClaimBudget(long prevClaimBudget) throws CommandException {
        if (this.isSubtract && (this.amount > prevClaimBudget)) {
            throw new CommandException(Messages.MESSAGE_OVER_CLAIM);
        }
        long newClaimBudget;
        if (this.isSubtract) {
            newClaimBudget = prevClaimBudget - this.amount;
        } else {
            newClaimBudget = prevClaimBudget + this.amount;
        }
        return new Money(String.valueOf(newClaimBudget));
    }

    /**
     * Returns a  Person object which contains the new claim budget.
     * Other variables of the person remains unchanged.
     *
     * @param personToEdit Person object (Old).
     * @param claimBudget Money object which reflects the new claim budget user has.
     * @return Person Object that contains the new claim budget whilst other variables remain unchanged.
     */
    public Person postClaimPerson(Person personToEdit, Money claimBudget) {
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getSalary(), claimBudget,
                    personToEdit.getDepartment(), personToEdit.getDob());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Both are the same instance
        }
        if (other == null || getClass() != other.getClass()) {
            return false; // Other is not an instance of ClaimCommand
        }
        ClaimCommand that = (ClaimCommand) other;
        return index.equals(that.index)
                && isSubtract.equals(that.isSubtract)
                    && amount == that.amount;
    }
}

package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Money;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ClaimCommand extends Command {

    public static final String COMMAND_WORD = "claim";
    public static final String MESSAGE_EMPTY = "Kindly state the index and amount that the employee hopes to process!";
    public static final String AMOUNT_EMPTY = "Kindly state the amount that the employee hopes to process!";
    public static final String CLAIM_SUCCESS = "Claim has been successfully processed!\n";

    private final Index index;
    private final Boolean isSubtract;
    private final long amount;

    public ClaimCommand(Index index, Boolean isSubtract, long amount) {
        this.index = index;
        this.isSubtract = isSubtract;
        this.amount = amount;
    }

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

        return new CommandResult(String.format("%s Remaining claim %s has: %s", CLAIM_SUCCESS, editedPerson.getName(), claimBudget));
    }

    private Money calculateNewClaimBudget(long prevClaimBudget) throws CommandException{
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

    private Person postClaimPerson(Person personToEdit, Money claimBudget) {
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getSalary(), claimBudget,
                    personToEdit.getDepartment(), personToEdit.getDob());
    }
}

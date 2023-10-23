package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Leave;
import seedu.address.model.person.Person;

/**
 * Adds leave months for an employee.
 */
public class LeaveCommand extends Command {

    public static final String COMMAND_WORD = "leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds leave months for an employee.\n"
            + "Parameters: INDEX (must be a positive integer) " + PREFIX_MONTH + "MONTHS\n"
            + "Format: MONTHS must be integers separated by commas without spaces. "
            + "1: Jan, 2: Feb, ..., 12: Dec.\n"
            + "Positive MONTHS add leaves on the specified months and negative MONTHS remove them.\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MONTH + "3,-4 to add leave in March and remove leave in April for the 1st employee in the list.";

    public static final String MESSAGE_AMBIGUOUS = "Please check your MONTHS. Ambiguous leave(s) assignment.\n";
    public static final String MESSAGE_EMPTY = "MONTHS cannot be empty.\n";
    public static final String MESSAGE_INVALID_MONTH = "Please check your MONTHS. Invalid month provided.\n";
    public static final String MESSAGE_LEAVE_SUCCESS = "Leave(s) successfully updated for employee: %1$s.\n"
            + "Current leave(s): %2$s";
    public static final String MESSAGE_NOT_A_NUMBER = "Please check your MONTHS. Some is not a number.\n";
    public static final String MESSAGE_NOT_EDITED = "The employee's leave(s) does not change from previous state: %1$s";
    public static final String MESSAGE_SPACES_DETECTED = "Spaces detected in your MONTHS.\n";

    private final Index index;
    private final String change;

    /**
     * Constructs a LeaveCommand to update the {@code Leave} of a {@code Person}
     * with the given index.
     * @param index
     * @param change
     */
    public LeaveCommand(Index index, String change) {
        requireNonNull(index);
        requireNonNull(change);

        this.index = index;
        this.change = change;
    }

    @Override
    public CommandResult execute(Model model, String commandText) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person oldPerson = lastShownList.get(index.getZeroBased());
        Leave oldLeave = oldPerson.getLeave();
        Leave newLeave;
        try {
            newLeave = oldLeave.update(change);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        if (oldLeave.equals(newLeave)) {
            throw new CommandException(String.format(MESSAGE_NOT_EDITED, oldLeave.toString()));
        }

        Person newPerson = new Person(oldPerson.getName(), oldPerson.getPhone(), oldPerson.getEmail(),
                oldPerson.getAddress(), oldPerson.getSalary(), oldPerson.getClaimBudget(),
                oldPerson.getDepartment(), oldPerson.getDob(), newLeave);

        model.setPerson(oldPerson, newPerson);
        model.addCommandText(commandText);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_LEAVE_SUCCESS, newPerson.getName(), newLeave.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LeaveCommand)) {
            return false;
        }

        LeaveCommand otherCommand = (LeaveCommand) other;
        return index.equals(otherCommand.index)
                && change.equals(otherCommand.change);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("leave", change)
                .toString();
    }
}

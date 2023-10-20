package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists persons with planned leave dates in a certain month in a certain department.
 */
public class ViewLeaveCommand extends Command {


    public static final String COMMAND_WORD = "view_leave";

    public static final String MESSAGE = Messages.MESSAGE_VIEW_LEAVE_SUCCESS;

    public final Predicate<Person> combinedPredicate;

    /**
     * Initializes ViewLeaveCommand with HasLeaveAnyMonthPredicate predicate.
     */
    public ViewLeaveCommand(Predicate<Person> combinedPredicate) {
        this.combinedPredicate = combinedPredicate;
    }

    @Override
    public CommandResult execute(Model model, String commandText) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(combinedPredicate);
        return new CommandResult(String.format(MESSAGE, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewLeaveCommand)) {
            return false;
        }

        ViewLeaveCommand otherCommand = (ViewLeaveCommand) other;
        return combinedPredicate.equals(otherCommand.combinedPredicate);
    }
}

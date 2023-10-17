package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Lists persons with planned leave dates in a certain month in a certain department.
 */
public class ViewLeaveCommand extends Command {


    public static final String COMMAND_WORD = "view_leave";

    public static final String message = Messages.MESSAGE_VIEW_LEAVE_SUCCESS;

    public final Predicate<Person> combinedPredicate;

    /**
     * Initializes ViewLeaveCommand with HasLeaveAnyMonthPredicate predicate.
     */
    public ViewLeaveCommand(Predicate<Person> combinedPredicate) {
        this.combinedPredicate = combinedPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(combinedPredicate);
        return new CommandResult(String.format(message, model.getFilteredPersonList().size()));
    }
}
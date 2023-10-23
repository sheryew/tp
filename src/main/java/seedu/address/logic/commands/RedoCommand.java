package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redo the most recent command that was undone.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redo the most recent command that was undone.";

    public static final String MESSAGE_SUCCESS =
            "The last command that modified the employee list has been redone!\n"
            + "Successfully redone the following command: %1$s";

    @Override
    public CommandResult execute(Model model, String cmd) throws CommandException {
        requireNonNull(model);
        try {
            String commandText = model.redo();
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, commandText));
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
    }
}

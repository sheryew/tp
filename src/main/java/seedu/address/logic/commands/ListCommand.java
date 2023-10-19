package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all persons or filtered persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public final String message;

    public final Predicate<Person> predicate;

    /**
     * Initializes ListCommand non-filtered list
     */
    public ListCommand() {
        this.predicate = PREDICATE_SHOW_ALL_PERSONS;
        this.message = Messages.MESSAGE_LIST_SUCCESS;
    }

    /**
     * Initializes ListCommand for filtered List
     *
     * @param predicate predicate for the filtering
     */
    public ListCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
        this.message = Messages.MESSAGE_FILTER_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(message, model.getFilteredPersonList().size()));
    }
}

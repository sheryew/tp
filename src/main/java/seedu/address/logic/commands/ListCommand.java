package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all employees";

    public static final String MESSAGE_FILTER_SUCCESS = "Listed filtered employees";

    public final String message;

    public final Predicate<Person> predicate;

    public ListCommand() {
        this.predicate = PREDICATE_SHOW_ALL_PERSONS;
        this.message = MESSAGE_SUCCESS;
    }

    public ListCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
        this.message = MESSAGE_FILTER_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(message);
    }
}

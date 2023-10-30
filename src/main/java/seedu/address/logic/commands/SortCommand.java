package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonComparator;

/**
 * Sorts the employee list based on the given parameter.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String SORT_DESC = "desc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the employee list based on the given parameter.\n"
            + "Format: sort name / phone / email / address / salary / claim / dep / dob [desc]\n"
            + "Choose one parameter to sort. [desc] to sort in descending order is optional.\n"
            + "Example: " + COMMAND_WORD + " name desc to sort based on name in descending order.";

    public static final String MESSAGE_SUCCESS = "The employee list is now sorted!";

    public static final String ERROR_MESSAGE = "Invalid parameter to sort!\n" + MESSAGE_USAGE;

    private final boolean desc;
    private final Comparator<Person> comparator;

    /**
     * Constructs a SortCommand.
     * @param param parameter to sort
     */
    public SortCommand(String param) {
        this.comparator = new PersonComparator(param);
        this.desc = false;
    }

    /**
     * Constructs a SortCommand.
     * @param param parameter to sort
     * @param desc descending
     */
    public SortCommand(String param, boolean desc) {
        this.comparator = new PersonComparator(param);
        this.desc = desc;
    }

    @Override
    public CommandResult execute(Model model, String commandText) throws CommandException {
        requireNonNull(model);
        try {
            if (desc) {
                model.sort(comparator.reversed());
            } else {
                model.sort(comparator);
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            model.addCommandText(commandText);
            return new CommandResult(String.format(MESSAGE_SUCCESS, commandText));
        } catch (IllegalArgumentException e) {
            throw new CommandException(ERROR_MESSAGE);
        }
    }
}

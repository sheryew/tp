package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows all employees' birthday in the given month/current month/
 */
public class BirthdayCommand extends Command {
    public static final String COMMAND_WORD = "birthday";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Gives a list of all employees who have birthdays in the given month.\n"
            + "Parameters (Optional): "
            + PREFIX_MONTH + "MONTH";
    public static final String MESSAGE_SUCCESS = Messages.MESSAGE_LIST_SUCCESS;
    public static final String MESSAGE_FAILURE = Messages.MESSAGE_BIRTHDAY_FAILURE;
    private final Predicate<Person> predicate;

    /**
     * Creates a BirthdayCommand to show all employees whose birthday fall in the given month
     * @param predicate a predicate that is used for comparison
     */
    public BirthdayCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    public Predicate<Person> getPredicate() {
        return this.predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_FAILURE);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().size()));
    }

}

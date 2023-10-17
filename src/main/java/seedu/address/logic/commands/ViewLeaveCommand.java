package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Lists persons with planned leave dates in a certain month in a certain department.
 */
public class ViewLeaveCommand extends Command {


    public static final String COMMAND_WORD = "view_leave";

    public final String message;

    public final ArrayList<Predicate<Person>> predicateArrayList;

    /**
     * Initializes ViewLeaveCommand with no predicates.
     */
    public ViewLeaveCommand() {
        this.message = "";
        this.predicateArrayList = new ArrayList<>();
    }

    /**
     * Adds a new predicate (filter) to the predicate ArrayList.
     *
     * @param predicate predicate for the filtering
     */
    public void addPredicate(Predicate<Person> predicate) {
        this.predicateArrayList.add(predicate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (Predicate<Person> predicate : predicateArrayList) {
            model.updateFilteredPersonList(predicate);
        }
        return new CommandResult(String.format(message, model.getFilteredPersonList().size()));
    }
}
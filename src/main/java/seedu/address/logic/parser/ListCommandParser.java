package seedu.address.logic.parser;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Department;
import seedu.address.model.person.MatchingDepartmentPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;

public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args arguments
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEPARTMENT);
        if (argMultimap.getValue(PREFIX_DEPARTMENT).isEmpty()) {
            return new ListCommand();
        }
        try {
            Department filteringDepartment = new Department(argMultimap.getValue(PREFIX_DEPARTMENT).get());
            return new ListCommand(new MatchingDepartmentPredicate(filteringDepartment));
        } catch (IllegalArgumentException e) {
            throw new ParseException(Messages.MESSAGE_EMPTY_DEPARTMENT_FILTER);
        }
    }
}
